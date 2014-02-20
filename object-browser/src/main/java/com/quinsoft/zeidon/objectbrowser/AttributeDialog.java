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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.objectdefinition.ViewAttribute;
import com.quinsoft.zeidon.objectdefinition.ViewEntity;

/**
 * @author DG
 *
 */
class AttributeDialog extends JDialog
{
    private static final long serialVersionUID = 1L;
    
    private static String[] COLS = { "Attr Name", "Value", "UP?" };
    private static String[] LINKED_COLS = { "Entity Name", "Entity Key", "OI ID" };

    private final BrowserEnvironment env;
    private final JTable attributeTable;
    private       EntityInstance currentEntityInstance;
    private final JCheckBox showHidden;
    private final JCheckBox showNull;
    private final JTable linkedTable;
    private final JSplitPane splitPane;

    AttributeDialog( BrowserEnvironment environment, JFrame parentFrame )
    {
        super( );
        this.env = environment;
        setName("AttributeDialog");
        setTitle( "Attribute List -- No entity selected" );

        JPanel optionPane = new JPanel();
        new BoxLayout( optionPane, BoxLayout.X_AXIS );
        showHidden = new JCheckBox("Show Hidden", this.env.isShowHiddenAttributes() );
        optionPane.add( showHidden );
        showHidden.addItemListener( new ItemListener(){
            @Override
            public void itemStateChanged( ItemEvent e )
            {
                env.setShowHiddenAttributes( showHidden.isSelected() );
                refresh();
            }} );
        
        showNull = new JCheckBox("Show Null", this.env.isShowNullAttributes() );
        showNull.addItemListener( new ItemListener(){
            @Override
            public void itemStateChanged( ItemEvent e )
            {
                env.setShowNullAttributes( showNull.isSelected() );
                refresh();
            }} );

        optionPane.add( showNull );
        
        add( optionPane, BorderLayout.NORTH );
        
        attributeTable = new JTable();
        attributeTable.setName( "AttributeTableDialog" );
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers( COLS );
        attributeTable.setModel( model );

        linkedTable = new JTable();
        linkedTable.setName( "AttributeLinkedTable" );
        model = new DefaultTableModel();
        model.setColumnIdentifiers( LINKED_COLS );
        linkedTable.setModel( model );

        JScrollPane scrollAttr = new JScrollPane( attributeTable );
        JScrollPane scrollLinked = new JScrollPane( linkedTable );
        
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollAttr, scrollLinked  );
        splitPane.setName( "AttributeSplitPane" );

        add( splitPane, BorderLayout.CENTER );
        
        setSize( 200, 400 );
        setVisible( true );
    }

    void refresh()
    {
        if ( currentEntityInstance == null )
            return;
        
        setEntity( currentEntityInstance.getViewEntity(), currentEntityInstance );
    }
    
    void setEntity( ViewEntity viewEntity, EntityInstance ei )
    {
        splitPane.setDividerLocation( 0.900 );
        currentEntityInstance = ei;
        setTitle( viewEntity.getName() );

        DefaultTableModel attrModel = (DefaultTableModel) attributeTable.getModel();
        while ( attrModel.getRowCount() > 0 )
            attrModel.removeRow( 0 );

        DefaultTableModel linkedModel = (DefaultTableModel) linkedTable.getModel();
        while ( linkedModel.getRowCount() > 0 )
            linkedModel.removeRow( 0 );
        
        if ( ei != null )
        {
            Object[] row = new Object[COLS.length];

            row[0] = "*Entity Key*";
            row[1] = ei.getEntityKey();
            row[2] = "";
            attrModel.addRow( row );

            for ( ViewAttribute viewAttribute : viewEntity.getAttributes() )
            {
                if ( ! viewAttribute.isKey() )
                {
                    if ( viewAttribute.isHidden() && ! env.isShowHiddenAttributes() )
                        continue;
                    
                    if ( ei.isAttributeNull( viewAttribute ) && ! env.isShowNullAttributes() )
                        continue;
                }
                
                int col = 0;
                row[col++] = viewAttribute.getName();
                row[col++] = ei.getStringFromAttribute( viewAttribute );
                row[col++] = ei.isAttributeUpdated( viewAttribute ) ? "Y" : "";
                attrModel.addRow( row );
            }
            
            row = new Object[LINKED_COLS.length];
            for ( EntityInstance linked : ei.getLinkedInstances() )
            {
                int col = 0;
                row[col++] = linked.getViewEntity().getName();
                row[col++] = linked.getEntityKey();
                row[col++] = linked.getObjectInstanceId();
                linkedModel.addRow( row );
            }
        }
    }
}
