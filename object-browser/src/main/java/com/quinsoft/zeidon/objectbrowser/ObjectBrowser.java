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

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.quinsoft.zeidon.BrowserStarter;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.objectbrowser.ViewChooser.ViewSelected;
import com.quinsoft.zeidon.standardoe.JavaObjectEngine;

/**
 * @author DG
 *
 */
public class ObjectBrowser implements ViewSelected
{
    private final BrowserEnvironment env;
    
    private JFrame          mainFrame;
    private OiDisplayDialog oiDisplay;
    private Set<JDialog>    childFrames; 

    public ObjectBrowser( ObjectEngine oe )
    {
        super();
        env = new BrowserEnvironment( oe, this );
        childFrames = new HashSet<JDialog>();
    }

    protected void startup()
    {
        // Create and set up the window.
        mainFrame = new JFrame();
        mainFrame.setTitle( "Zeidon Object Browser" );
        mainFrame.setName( "Object Browser" );
        ViewChooser viewChooser = new ViewChooser( env, this );
        mainFrame.getContentPane().add( viewChooser );
        oiDisplay = new OiDisplayDialog( env, mainFrame, true );
        oiDisplay.setName( "MainOiDisplay" );
        childFrames.add( oiDisplay );
        
        BrowserEventHandler listener = new BrowserEventHandler();
        mainFrame.addWindowStateListener( listener );
        mainFrame.addWindowListener( listener );
        mainFrame.pack();

        env.restore( this );
        env.restore( oiDisplay );
        
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

    public static void main( String[] args ) throws InterruptedException
    {
        ObjectEngine oe = JavaObjectEngine.getInstance();
        Task zencas = oe.createTask( "ZENCAs" );
        View v = zencas.activateOiFromFile( "mStudenC", "../JOE/testdata/ZENCAs/mStudenCOI.por", null );
        v.setName( "TestView" );
        v = zencas.activateOiFromFile( "mFAProf", "../JOE/testdata/ZENCAs/mFAProfO.por", null );
        v.setName( "mFAProf" );
        BrowserStarter starter = new Starter();
        starter.startBrowser( oe );
        System.out.println( "Done" );
    }

    @Override
    public void viewSelected( final View view )
    {
        // Use invokeLater otherwise toFront() won't always work.
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                oiDisplay.toFront();
                View v = view.newView();  // Copy the view so that we don't mess up the original cursors.
                v.setLazyLoad( false );   // Turn off lazy loading so browser doesn't change data.
                oiDisplay.displayView( v );
            }
        });
    }
    
    void saveEnvironment()
    {
        env.save( ObjectBrowser.this );
        oiDisplay.saveEnvironment();
        env.getOe().getSystemTask().log().info( "Browser environment saved" );
    }
    
    private class BrowserEventHandler extends WindowAdapter
    {
        public void windowClosing(WindowEvent e)
        {
            env.save( ObjectBrowser.this );
            oiDisplay.shutdown();
            mainFrame.setVisible(false);
            for ( JDialog dialog : childFrames )
                dialog.dispose();
            mainFrame.dispose();
            mainFrame = null;
            childFrames = null;
            oiDisplay = null;
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
            
            for ( JDialog child : childFrames )
            {
                child.setVisible( visible );
            }
        }
    }

    public Component getMainFrame()
    {
        return mainFrame;
    }
}
