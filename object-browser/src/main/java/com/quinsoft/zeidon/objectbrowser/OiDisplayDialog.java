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
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectbrowser.OiDisplay.EntitySelectedListener;
import com.quinsoft.zeidon.objectdefinition.ViewEntity;

/**
 * This is the frame around the OI display.
 * 
 * @author DG
 *
 */
class OiDisplayDialog extends JDialog implements EntitySelectedListener, ActionListener
{
    private static final long serialVersionUID = 1L;
    private static final String FIRST_CURSOR = "FirstCursor";
    private static final String PREV_CURSOR  = "PreviousCursor";
    private static final String NEXT_CURSOR  = "NextCursor";
    private static final String LAST_CURSOR  = "LastCursor";
    
    private final BrowserEnvironment env;
    private final JFrame  parentFrame;
    private final boolean isMainOiDisplay;
    private final AttributeDialog attributeDialog;
    private       ViewEntity selectedViewEntity;
    private final Set<JDialog> childFrames; 
    private final BorderLayout borderLayout;
    private       OiDisplay oiDisplay;
    private       View view;
    private final TwinDialog twinDialog;

    OiDisplayDialog( BrowserEnvironment env, JFrame parentFrame, boolean isMainOiDisplay )
    {
        super( );
        this.env = env;
        this.parentFrame = parentFrame;
        this.isMainOiDisplay = isMainOiDisplay;
        childFrames = new HashSet<JDialog>();
        setTitle( "No OI selected" );
        setSize( new Dimension( 1000, 200 ) );
        borderLayout = new BorderLayout();
        setLayout( borderLayout );
        
        //
        // Set up buttons.
        //
        JPanel buttonPane = new JPanel();
        buttonPane.add( new JLabel( "Cursors:" ) );
        addButton( buttonPane, "First", FIRST_CURSOR );
        addButton( buttonPane, "Prev", PREV_CURSOR );
        addButton( buttonPane, "Next", NEXT_CURSOR );
        addButton( buttonPane, "Last", LAST_CURSOR );
        add( buttonPane, BorderLayout.NORTH );
        
        attributeDialog = new AttributeDialog( env, this.parentFrame );
        attributeDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        childFrames.add( attributeDialog );
        env.restore( attributeDialog );
        
        twinDialog = new TwinDialog( env, this.parentFrame );
        twinDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        childFrames.add( twinDialog );
        env.restore( twinDialog );
        
        if ( isMainOiDisplay )
            setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); 
        
        addComponentListener( new DisplayVisibleHandler() );
        setVisible( true );
    }
    
    void saveEnvironment()
    {
        env.save( this );
        env.save( attributeDialog );
        env.save( twinDialog );
    }

    void shutdown()
    {
        saveEnvironment();
    }
    
    private void addButton( JPanel buttonPane, String title, String command )
    {
        JButton button = new JButton( title );
        button.setActionCommand( command );
        button.addActionListener( this );
        buttonPane.add( button );
    }
    
    @Override
    public void setTitle( String title )
    {
        if ( isMainOiDisplay )
            super.setTitle( "** Main Display ** " + title );
        else
            super.setTitle( title );
    }
    
    @Override
    public void dispose()
    {
        for ( JDialog dialog : childFrames )
            dialog.dispose();
        super.dispose();
    }
    
    void displayView( View view )
    {
        this.view = view;
        setTitle( view.getViewOd().getName() );
        if ( oiDisplay != null )
            remove( oiDisplay );
        
        oiDisplay = new OiDisplay( env, view, this, this );

        add( oiDisplay, BorderLayout.CENTER );
        oiDisplay.revalidate();
    }

    @Override
    public void entitySelected( ViewEntity viewEntity, EntityInstance ei )
    {
        selectedViewEntity = viewEntity;
        attributeDialog.setEntity( viewEntity, ei );
        twinDialog.setEntityInstance( ei );
    }

    @Override
    public void scaleChanged( View view, Point p )
    {
        ViewEntity ve = selectedViewEntity; // Save this because it'll be changed by displayView.
        displayView( view );
        oiDisplay.setSelectedEntityFromViewEntity( ve );
        oiDisplay.repositionScroll( p );
    }

    private class DisplayVisibleHandler extends ComponentAdapter
    {
        @Override
        public void componentHidden(ComponentEvent evt)
        {
            for ( JDialog child : childFrames )
            {
                child.setVisible( false );
            }
        }

        @Override
        public void componentShown(ComponentEvent evt)
        {
            for ( JDialog child : childFrames )
            {
                child.setVisible( true );
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent action)
    {
        if ( selectedViewEntity == null ) // Possible when dialog is first displayed.
            return;
        
        if ( action.getActionCommand().equals( FIRST_CURSOR ) )
            view.cursor( selectedViewEntity ).setFirst();
        else
        if ( action.getActionCommand().equals( PREV_CURSOR ) )
            view.cursor( selectedViewEntity ).setPrev();
        else
        if ( action.getActionCommand().equals( NEXT_CURSOR ) )
            view.cursor( selectedViewEntity ).setNext();
        else
        if ( action.getActionCommand().equals( LAST_CURSOR ) )
            view.cursor( selectedViewEntity ).setLast();
        else
            throw new ZeidonException( "Internal action error" );
        
        entitySelected( selectedViewEntity, view.cursor( selectedViewEntity ).getEntityInstance() );
        oiDisplay.repaint();
    }
}
