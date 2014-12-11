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

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.ToolTipManager;

import com.quinsoft.zeidon.ObjectEngine;

/**
 * The main Frame for the stand-alone OB.
 *
 * @author DG
 *
 */
public class ObjectBrowser
{
    private final FrameBrowserEnvironment env;
    private JFrame mainFrame;

    public ObjectBrowser( ObjectEngine oe )
    {
        super();
        env = new FrameBrowserEnvironment( oe, this );
    }

    protected void startup()
    {
        // Create and set up the window.
        mainFrame = new JFrame();
        mainFrame.setTitle( "Zeidon Object Browser" );
        mainFrame.setName( "Object Browser" );
        mainFrame.getContentPane().add( new MainPanel( env ) );

        BrowserEventHandler listener = new BrowserEventHandler();
        mainFrame.addWindowStateListener( listener );
        mainFrame.addWindowListener( listener );
        mainFrame.pack();

        env.restoreEnvironment();

        // Display the window.
        mainFrame.setVisible( true );
        
        // Use invokeLater otherwise toFront() won't always work.
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                mainFrame.toFront();
            }
        });

    }

    void saveEnvironment()
    {
        env.saveEnvironment();
        env.getOe().getSystemTask().log().info( "Browser environment saved" );
    }

    public JFrame getFrame()
    {
        return mainFrame;
    }

    private class BrowserEventHandler extends WindowAdapter
    {
        @Override
        public void windowClosing(WindowEvent e)
        {
            env.saveEnvironment();
            mainFrame.setVisible(false);
            mainFrame.dispose();
            mainFrame = null;
        }

        @Override
        public void windowStateChanged(WindowEvent e)
        {
            boolean visible = true;
            switch ( e.getNewState() )
            {
                case Frame.ICONIFIED:
                    visible = false;
                    break;
            }
        }
    }

    public Component getMainFrame()
    {
        return mainFrame;
    }
}
