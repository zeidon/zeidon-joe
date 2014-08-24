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

        leftTabbedPane.setMnemonicAt(0, KeyEvent.VK_T);
        leftTabbedPane.setMnemonicAt(1, KeyEvent.VK_A);
        leftTabbedPane.setMnemonicAt(2, KeyEvent.VK_W);

        OiDisplayPanel oiDisplay = env.createOiDisplay( this );
        JSplitPane splitPane = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT, leftTabbedPane, oiDisplay );

        add( splitPane, BorderLayout.CENTER );
    }

    public JTabbedPane getLeftTabbedPane()
    {
        return leftTabbedPane;
    }
}
