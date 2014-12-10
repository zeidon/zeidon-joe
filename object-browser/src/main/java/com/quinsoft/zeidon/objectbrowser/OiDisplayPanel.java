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
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectbrowser.OiDisplay.EntitySelectedListener;
import com.quinsoft.zeidon.objectdefinition.EntityDef;

/**
 * This is the main panel around the OI display.
 *
 * @author DG
 *
 */
class OiDisplayPanel extends JPanel implements EntitySelectedListener, ActionListener
{
    private static final long serialVersionUID = 1L;
    private static final String FIRST_CURSOR = "FirstCursor";
    private static final String PREV_CURSOR  = "PreviousCursor";
    private static final String NEXT_CURSOR  = "NextCursor";
    private static final String LAST_CURSOR  = "LastCursor";

    private final BrowserEnvironment env;
    private       EntityDef selectedEntityDef;
    private final BorderLayout borderLayout;
    private       OiDisplay oiDisplay;
    private       View view;

    OiDisplayPanel( BrowserEnvironment env )
    {
        super( );
        this.env = env;
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
        setVisible( true );
    }

    private void addButton( JPanel buttonPane, String title, String command )
    {
        JButton button = new JButton( title );
        button.setActionCommand( command );
        button.addActionListener( this );
        buttonPane.add( button );
    }

    void displayView( View view )
    {
        this.view = view;
        if ( oiDisplay != null )
            remove( oiDisplay );

        oiDisplay = new OiDisplay( env, view, this, this );

        add( oiDisplay, BorderLayout.CENTER );
        oiDisplay.revalidate();
    }

    EntitySquare setSelectedEntity( EntityDef entityDef )
    {
        oiDisplay.setSelectedEntityFromEntityDef( entityDef );
        return oiDisplay.getSelectedEntity();
    }

    @Override
    public void entitySelected( EntityDef entityDef, EntityInstance ei )
    {
        selectedEntityDef = entityDef;
        env.entitySelected( entityDef, ei );
//        twinDialog.setEntityInstance( ei );
    }

    @Override
    public void scaleChanged( View view, Point p )
    {
        EntityDef ve = selectedEntityDef; // Save this because it'll be changed by displayView.
        displayView( view );
        oiDisplay.setSelectedEntityFromEntityDef( ve );
        oiDisplay.repositionScroll( p );
    }

    @Override
    public void actionPerformed(ActionEvent action)
    {
        if ( selectedEntityDef == null ) // Possible when dialog is first displayed.
            return;

        if ( action.getActionCommand().equals( FIRST_CURSOR ) )
            view.cursor( selectedEntityDef ).setFirst();
        else
        if ( action.getActionCommand().equals( PREV_CURSOR ) )
            view.cursor( selectedEntityDef ).setPrev();
        else
        if ( action.getActionCommand().equals( NEXT_CURSOR ) )
            view.cursor( selectedEntityDef ).setNext();
        else
        if ( action.getActionCommand().equals( LAST_CURSOR ) )
            view.cursor( selectedEntityDef ).setLast();
        else
            throw new ZeidonException( "Internal action error" );

        entitySelected( selectedEntityDef, view.cursor( selectedEntityDef ).getEntityInstance() );
        oiDisplay.repaint();
    }
}
