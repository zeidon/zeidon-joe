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

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.EntityDef;

/**
 * Displays attribute values for a selected entity instance.
 *
 * @author DG
 *
 */
public class AttributePanel extends JPanel
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

    AttributePanel( BrowserEnvironment environment )
    {
        super(  new BorderLayout() );
        this.env = environment;
        setName("AttributeDialog");

        JPanel optionPane = new JPanel();
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
        JPanel linkedPanel = new JPanel( new BorderLayout() );
        linkedPanel.add( new JLabel( "Linked Entity Instances" ), BorderLayout.NORTH );
        linkedPanel.add( scrollLinked, BorderLayout.CENTER );

        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollAttr, linkedPanel  );
        splitPane.setName( "AttributeSplitPane" );

        add( splitPane, BorderLayout.CENTER );

//        setSize( 200, 400 );
        setVisible( true );
    }

    void refresh()
    {
        if ( currentEntityInstance == null )
            return;

        setEntity( currentEntityInstance.getEntityDef(), currentEntityInstance );
    }

    void setEntity( EntityDef entityDef, EntityInstance ei )
    {
        splitPane.setDividerLocation( 0.900 );
        currentEntityInstance = ei;

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

            for ( AttributeDef attributeDef : entityDef.getAttributes() )
            {
                if ( ! attributeDef.isKey() )
                {
                    if ( attributeDef.isHidden() && ! env.isShowHiddenAttributes() )
                        continue;

                    if ( ei.isAttributeNull( attributeDef ) && ! env.isShowNullAttributes() )
                        continue;
                }

                int col = 0;
                row[col++] = attributeDef.getName();
                row[col++] = ei.getStringFromAttribute( attributeDef, null );
                row[col++] = ei.isAttributeUpdated( attributeDef ) ? "Y" : "";
                attrModel.addRow( row );
            }

            row = new Object[LINKED_COLS.length];
            for ( EntityInstance linked : ei.getLinkedInstances() )
            {
                int col = 0;
                row[col++] = linked.getEntityDef().getName();
                row[col++] = linked.getEntityKey();
                row[col++] = linked.getObjectInstanceId();
                linkedModel.addRow( row );
            }
        }
    }
}
