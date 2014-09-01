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
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

/**
 * The main panel for the object browser.
 *
 * @author dgc
 *
 */
public class MainPanel extends JPanel
{
    private static final long serialVersionUID = 1L;

    private final BrowserEnvironment env;

    private JTabbedPane leftTabbedPane;

    /**
     *
     */
    public MainPanel( BrowserEnvironment env )
    {
        super( new BorderLayout() );
        this.env = env;

        env.createAttributePanel();
        leftTabbedPane = new JTabbedPane();
        leftTabbedPane.addTab( "Tasks/Views", new ViewChooser( env ) );
        leftTabbedPane.addTab( "Attributes", env.getAttributePanel() );
        leftTabbedPane.addTab( "Twins", new TwinInstancesPanel( this.env ) );
        leftTabbedPane.addTab( "Entity List", new EntityListPanel( this.env ) );

        leftTabbedPane.setMnemonicAt(0, KeyEvent.VK_T);
        leftTabbedPane.setMnemonicAt(1, KeyEvent.VK_A);
        leftTabbedPane.setMnemonicAt(2, KeyEvent.VK_W);
        leftTabbedPane.setMnemonicAt(3, KeyEvent.VK_E);

        OiDisplayPanel oiDisplay = env.createOiDisplay( this );
        JSplitPane splitPane = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT, leftTabbedPane, oiDisplay );

        add( splitPane, BorderLayout.CENTER );
    }

    public JTabbedPane getLeftTabbedPane()
    {
        return leftTabbedPane;
    }
}
