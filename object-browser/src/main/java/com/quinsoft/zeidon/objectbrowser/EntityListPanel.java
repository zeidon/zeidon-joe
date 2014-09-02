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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Enumeration;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.objectdefinition.ViewEntity;
import com.quinsoft.zeidon.objectdefinition.ViewOd;

/**
 * @author dgc
 *
 */
public class EntityListPanel extends JPanel
{

    private static final long serialVersionUID = 1L;
    private static final DefaultMutableTreeNode EMPTY_ROOT = new DefaultMutableTreeNode( "No OI Selected" );

    private final BrowserEnvironment env;
    private JTree jtree;
    private View view;

    /**
     *
     */
    public EntityListPanel( BrowserEnvironment environment )
    {
        super( new BorderLayout() );
        env = environment;
        setName("EntityDialog");
        jtree = new JTree( EMPTY_ROOT );

        JScrollPane scroll = new JScrollPane( jtree );
        add( scroll, BorderLayout.CENTER );
        env.setEntityListPanel( this );
    }

    private DefaultMutableTreeNode addViewEntity( ViewEntity viewEntity )
    {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode( viewEntity.getName() );
        for ( ViewEntity child : viewEntity.getChildren() )
            node.add(  addViewEntity( child ) );

        return node;
    }

    void setView( View view )
    {
        this.view = view;
        ViewOd viewOd = view.getViewOd();
        DefaultMutableTreeNode root = addViewEntity( viewOd.getRoot() );
        jtree = new JTree( root );
        for (int i = 0; i < jtree.getRowCount(); i++)
            jtree.expandRow(i);

        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) jtree.getCellRenderer();
        renderer.setLeafIcon( null );
        renderer.setClosedIcon( null );
        renderer.setOpenIcon( null );

        MouseListener ml = new MouseAdapter()
        {
            @Override
            public void mousePressed( MouseEvent e )
            {
                int selRow = jtree.getRowForLocation( e.getX(), e.getY() );
                TreePath selPath = jtree.getPathForLocation( e.getX(), e.getY() );
                if ( selPath == null )
                    return;

                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) selPath.getLastPathComponent();
                if ( selRow != -1 )
                {
                    if ( e.getClickCount() == 1 )
                    {
                        valueChanged( selectedNode );
                    }
                    else if ( e.getClickCount() == 2 )
                    {
                        valueChanged( selectedNode );
                    }
                }
            }
        };
        jtree.addMouseListener(ml);

        JScrollPane scroll = new JScrollPane( jtree );
        removeAll();
        add( scroll );
    }

    /**
     * This is called when a new entity instance is selected in the OI view.
     *
     * @param viewEntity
     */
    void setViewEntity( ViewEntity viewEntity )
    {
        String name = viewEntity.getName();

        DefaultMutableTreeNode root = (DefaultMutableTreeNode) jtree.getModel().getRoot();
        @SuppressWarnings("unchecked")
        Enumeration<DefaultMutableTreeNode> e = root.depthFirstEnumeration();
        while ( e.hasMoreElements() )
        {
            DefaultMutableTreeNode node = e.nextElement();
            if ( node.toString().equalsIgnoreCase( name ) )
            {
                TreePath path = new TreePath( node.getPath() );
                jtree.setSelectionPath( path );
                jtree.scrollPathToVisible( path );
                return;
            }
        }
    }

    /**
     * This gets called when the user changes the selected entity.
     */
    public void valueChanged( DefaultMutableTreeNode selectedNode )
    {
        String name = (String) selectedNode.getUserObject();
        ViewEntity viewEntity = view.getViewOd().getViewEntity( name );
        env.getOiDisplay().setSelectedEntity( viewEntity );
    }
}
