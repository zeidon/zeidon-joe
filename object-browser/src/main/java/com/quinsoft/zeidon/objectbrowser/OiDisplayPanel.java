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

package com.quinsoft.zeidon.objectbrowser;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
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
class OiDisplayPanel extends JPanel implements EntitySelectedListener, ActionListener, ClipboardOwner
{
    private static final long serialVersionUID = 1L;
    private static final String FIRST_CURSOR = "FirstCursor";
    private static final String PREV_CURSOR  = "PreviousCursor";
    private static final String NEXT_CURSOR  = "NextCursor";
    private static final String LAST_CURSOR  = "LastCursor";

    private final BrowserEnvironment env;
    private       EntityDef selectedEntityDef;
    private final BorderLayout borderLayout;
    protected     OiDisplay oiDisplay;
    private       View currentView;

    OiDisplayPanel( BrowserEnvironment env )
    {
        super( );
        this.env = env;
//        setSize( new Dimension( 1000, 200 ) );
        borderLayout = new BorderLayout();
        setLayout( borderLayout );

        //
        // Set up buttons.
        //
        JPanel buttonPane = new JPanel();
        buttonPane.add( new JLabel( "Cursors:" ) );
        addButton( buttonPane, "First", FIRST_CURSOR, "[Home]" );
        addButton( buttonPane, "Prev in OI", PREV_CURSOR, "[Page Up]" );
        addButton( buttonPane, "Next in OI", NEXT_CURSOR, "[Page Down]" );
        addButton( buttonPane, "Last", LAST_CURSOR, "[End]" );

        // Add a dummy, invisible label for spacing.  It's a hack but it's easy.
        JLabel dummy = new JLabel( "                               " );
        buttonPane.add( dummy );

        // Add a button who's only purpose is to be a mouse over tooltip.
        try
        {
            URL url = Resources.getResource("help-text.html.txt");
            String text = Resources.toString(url, Charsets.UTF_8);
            JButton button = new JButton( "Help" );
            button.setToolTipText( text );
            buttonPane.add( button );
        }
        catch ( Exception e )
        {
            env.getOe().getSystemTask().log().error( "Couldn't open help-text.html.txt" );
        }

        add( buttonPane, BorderLayout.NORTH );
        setVisible( true );
    }

    private void addButton( JPanel buttonPane, String title, String command, String tooltip )
    {
        JButton button = new JButton( title );
        button.setActionCommand( command );
        button.addActionListener( this );
        button.setToolTipText( tooltip );
        buttonPane.add( button );
    }

    void displayView( View view )
    {
        if ( oiDisplay != null )
            remove( oiDisplay );

        currentView = view;
        oiDisplay = new OiDisplay( env, view, this, this );

        add( oiDisplay, BorderLayout.CENTER );
        oiDisplay.revalidate();
    }

    View getDisplayedView()
    {
        return currentView;
    }

    void setFocusToDisplay()
    {
        oiDisplay.getSelectedEntity().requestFocus();
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
            oiDisplay.getView().cursor( selectedEntityDef ).setFirst();
        else
        if ( action.getActionCommand().equals( PREV_CURSOR ) )
            oiDisplay.getView().cursor( selectedEntityDef ).setPrevWithinOi();
        else
        if ( action.getActionCommand().equals( NEXT_CURSOR ) )
            oiDisplay.getView().cursor( selectedEntityDef ).setNextWithinOi();
        else
        if ( action.getActionCommand().equals( LAST_CURSOR ) )
            oiDisplay.getView().cursor( selectedEntityDef ).setLast();
        else
            throw new ZeidonException( "Internal action error" );

        entitySelected( selectedEntityDef, oiDisplay.getView().cursor( selectedEntityDef ).getEntityInstance() );
        oiDisplay.repaint();
    }

    @Override
    public void lostOwnership( Clipboard arg0, Transferable arg1 )
    {
        // Do nothing.
    }
}
