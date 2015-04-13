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
package com.quinsoft.zeidon.jconsole;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.management.MBeanServerConnection;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.objectbrowser.MainPanel;
import com.quinsoft.zeidon.standardoe.JavaObjectEngine;

/**
 * Main panel for Object browser plugin for JConsole.
 *
 * @author dgc
 *
 */
public class JConsoleBrowser extends JPanel
{
    private static final long serialVersionUID = 1L;
    private final JConsoleEnvironment env;

    /**
     * @param server2
     *
     */
    public JConsoleBrowser( MBeanServerConnection server )
    {
        super( new BorderLayout() );
        ObjectEngine oe = JavaObjectEngine.getInstance();
        env = new JConsoleEnvironment( oe, server );
        env.searchForObjectEngineBeans();
        final MainPanel main = new MainPanel( env );

        OeTableModel oeModel = new OeTableModel( env.getProxies() );
        final JTable table = new JTable( oeModel );
        table.setIntercellSpacing( new Dimension( 6, 3 ) );
        table.setRowHeight( table.getRowHeight() + 4 );
        JScrollPane oePane = new JScrollPane( table );
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int idx = table.getSelectedRow();
                env.oeSelected( idx );
                main.getLeftTabbedPane().setSelectedIndex( 0 ); // Change tab to Task/View lists.
            }
        });

        int tabIndex = main.getLeftTabbedPane().getTabCount();
        main.getLeftTabbedPane().addTab( "OE List", oePane );
        main.getLeftTabbedPane().setMnemonicAt( tabIndex, KeyEvent.VK_O );
        main.getLeftTabbedPane().setSelectedIndex( tabIndex );
        add( main, BorderLayout.CENTER );

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                env.restoreEnvironment();
            }
        });
    }


    // Return a new SwingWorker for UI update
    SwingWorker<?, ?> newSwingWorker()
    {
        return new BrowserSwingWorker(  );
    }
}
