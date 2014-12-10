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

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;

import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.EntityDef;

/**
 * Builds the square (well, rectangle) that depicts an entity in the browser.
 *
 */
public class EntitySquare extends JPanel implements MouseListener
{
    private static final long serialVersionUID = 1L;

    private static final int SMALLEST_WIDTH  = 10;
    private static final int SMALLEST_HEIGHT = 7;
    private static final int VERTICAL_PAD    = 3;
    private static final int HORIZONTAL_PAD  = 2;

    private static Color SELECTED_COLOR = Color.RED;
    private static Color ENTITY_EXISTS  = Color.GREEN;
    private static Color NULL_ENTITY    = Color.GRAY;
    private static Color OUT_OF_SCOPE   = Color.BLACK;
    private static Color NOT_LOADED     = Color.LIGHT_GRAY;

    private final OiDisplay          oiDisplay;
    private final EntityDefLayout    entityDefLayout;
    private final BrowserEnvironment env;
    private final Dimension          size;
    private final Dimension          paddedSize;
    private final Font font;
    
    private EntityInstance currentEi;

    EntitySquare( OiDisplay display, BrowserEnvironment environment, EntityDefLayout layout )
    {
        super();
        this.env = environment;
        oiDisplay = display;
        entityDefLayout = layout;
        size = new Dimension( SMALLEST_WIDTH * env.getPainterScaleFactor(), SMALLEST_HEIGHT * env.getPainterScaleFactor() );
        paddedSize = new Dimension( ( SMALLEST_WIDTH + HORIZONTAL_PAD * 2 ) * env.getPainterScaleFactor(),
                                    ( SMALLEST_HEIGHT + VERTICAL_PAD * 2 ) * env.getPainterScaleFactor() );
        setSize( size );
        font = new Font( Font.SANS_SERIF, Font.PLAIN, env.getPainterScaleFactor() );
        setCursor( new Cursor( Cursor.DEFAULT_CURSOR ) );
        addMouseListener( this );
        
        getInputMap().put( KeyStroke.getKeyStroke( "HOME" ),  "setFirst" );
        getActionMap().put("setFirst", new SetCursorAction( CursorPosition.FIRST ) );
        getInputMap().put( KeyStroke.getKeyStroke( "PAGE_UP" ),  "setPrev" );
        getActionMap().put("setPrev", new SetCursorAction( CursorPosition.PREV ) );
        getInputMap().put( KeyStroke.getKeyStroke( "PAGE_DOWN" ),  "setNext" );
        getActionMap().put("setNext", new SetCursorAction( CursorPosition.NEXT ) );
        getInputMap().put( KeyStroke.getKeyStroke( "END" ),  "setLast" );
        getActionMap().put("setLast", new SetCursorAction( CursorPosition.LAST ) );

        getInputMap().put( KeyStroke.getKeyStroke( "UP" ),  "moveUp" );
        getActionMap().put("moveUp", new ChangeSelectedEntityDefAction( 1 ) );
        getInputMap().put( KeyStroke.getKeyStroke( "DOWN" ),  "moveDown" );
        getActionMap().put("moveDown", new ChangeSelectedEntityDefAction( 2 ) );
        getInputMap().put( KeyStroke.getKeyStroke( "LEFT" ),  "moveLeft" );
        getActionMap().put("moveLeft", new ChangeSelectedEntityDefAction( 3 ) );
        getInputMap().put( KeyStroke.getKeyStroke( "RIGHT" ),  "moveRight" );
        getActionMap().put("moveRight", new ChangeSelectedEntityDefAction( 4 ) );

    }

    Point getTopAnchor()
    {
        Rectangle b = getBounds();
        return new Point( b.x + b.width / 2, b.y );
    }

    Point getBottomAnchor()
    {
        Rectangle b = getBounds();
        return new Point( b.x + b.width / 2, b.y + b.height );
    }

    static String getKeyString( EntityInstance ei, EntityDef entityDef )
    {
        StringBuilder builder = new StringBuilder();
        List<AttributeDef> keys = entityDef.getKeys();
        for ( AttributeDef key : keys )
        {
        	if ( ! ei.isHidden())
                builder.append( key.getName() ).append( ": " ).append( ei.getStringFromAttribute( key ) );
        	else
        	{
        		if (ei.isDeleted())
        		   builder.append( key.getName() ).append( ": DELETED" );
        		else if (ei.isExcluded())
         		   builder.append( key.getName() ).append( ": EXCLUDED" );
        		else
         		   builder.append( key.getName() ).append( ": HIDDEN" );
        	}
        }

        return builder.toString();
    }

    private View getView()
    {
        return oiDisplay.getView();
    }

    EntityDef getEntityDef()
    {
        return entityDefLayout.getEntityDef();
    }

    private String getSiblingCount( EntityCursor cursor )
    {
        EntityInstance ei = cursor.getEntityInstance();
        int num = 1;
        for ( EntityInstance t = ei; t.getPrevTwin() != null; t = t.getPrevTwin() )
            num++;

        int count = num;
        for ( EntityInstance t = ei; t != null && t.getNextTwin() != null; t = t.getNextTwin() )
            count++;

        StringBuilder sb = new StringBuilder();
        sb.append( num ).append( " of " ).append( count );

        List<String> flags = new ArrayList<String>();
        if ( ei.isCreated() )
            flags.add( "CR" );
        if ( ei.isDeleted() )
            flags.add( "DE" );
        if ( ei.isUpdated() )
            flags.add( "UP" );
        if ( ei.isIncluded() )
            flags.add( "IN" );
        if ( ei.isExcluded() )
            flags.add( "EX" );

        if ( flags.size() > 0 )
            sb.append( "  (" ).append( StringUtils.join( flags, "," ) ).append( ")" );

        return sb.toString();
    }

    private void paintCenteredText( Graphics2D graphics2, int y, String text, Color color )
    {
        Color prevColor = graphics2.getColor();
        if ( color != null )
            graphics2.setColor( color );

        FontMetrics fm = graphics2.getFontMetrics();
        int lth = fm.stringWidth( text );
        int mid = size.width / 2;
        graphics2.drawString( text, mid - lth/2, y );

        if ( color != null )
            graphics2.setColor( prevColor );
    }

    @Override
    public void paint(Graphics g)
    {
        Graphics2D graphics2 = (Graphics2D) g;
        EntityDef entityDef = getEntityDef();
        EntityCursor cursor = getView().cursor( entityDef );

        if ( oiDisplay.getSelectedEntity() == this )
            g.setColor( SELECTED_COLOR );
        else
        {
            switch ( cursor.getStatus() )
            {
                case NULL:
                    g.setColor( NULL_ENTITY );
                    break;

                case OUT_OF_SCOPE:
                    g.setColor( OUT_OF_SCOPE );
                    break;

                case NOT_LOADED:
                    g.setColor( NOT_LOADED );
                    break;

                default:
                    g.setColor( ENTITY_EXISTS );
            }
        }

        // Fill in the shape.
        graphics2.fillRoundRect(0, 0, size.width-1, size.height-1, 20, 20);

        // Draw the black outline.
        g.setColor( Color.black );
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, size.width-1, size.height-1, 20, 20);
        graphics2.draw(roundedRectangle);

        // Write the entity name
        graphics2.setFont( font );
        paintCenteredText( graphics2, env.getPainterScaleFactor(),entityDef.getName(), null );

        switch ( cursor.getStatus() )
        {
            case NULL:
                // Do nothing.
                break;

            case NOT_LOADED:
                paintCenteredText( graphics2, size.height / 2, "(Not yet loaded)", Color.WHITE );
                break;

            case OUT_OF_SCOPE:
                paintCenteredText( graphics2, size.height / 2, "Out of Scope", Color.WHITE );
                break;

            default:
                String s = getKeyString( cursor, entityDef );
                paintCenteredText( graphics2, size.height / 2, s, null );

                s = getSiblingCount( cursor );
                paintCenteredText( graphics2, size.height - env.getPainterScaleFactor(), s, null );
        }
        
        setToolTipText( cursor );
    }

    /**
     * Sets the tooltip text from the current selected EI.
     * 
     * @param cursor
     */
    private void setToolTipText( EntityCursor cursor )
    {
        EntityInstance ei = cursor.getEntityInstance();
        if ( ei == currentEi )
            return;

        currentEi = ei;
        
        if ( ei == null )
        {
            setToolTipText( "" );
            return;
        }
        
        StringBuilder html = new StringBuilder( "<html><table>");
        
        EntityDef entityDef = cursor.getEntityDef();
        for ( AttributeDef attributeDef : entityDef.getAttributes() )
        {
            if ( attributeDef.isHidden() )
                continue;
            
            if ( ei.getAttribute( attributeDef ).isNull() ) 
                continue;
                    
            String value = ei.getAttribute( attributeDef ).getString();
            
            // Skip null attributes.
            if ( StringUtils.isBlank( value ) )
                continue;

            html.append( "<tr><td>" ).append( attributeDef.getName() ).append( "</td>" );
            
            if ( StringUtils.length( value ) > 50 )
                value = value.substring( 0, 50 );
            html.append( "<td>" ).append( value ).append( "</td></tr>" );
        }
        
        html.append( "</table></html>" );
        setToolTipText( html.toString() );
    }

    @Override
    public void mouseClicked( MouseEvent arg0 )
    {
        requestFocus();
        EntitySquare prevSelected = oiDisplay.getSelectedEntity();
        if ( prevSelected == this )
            return;

        oiDisplay.setSelectedEntity( this );

        prevSelected.repaint();
        this.repaint();
    }

    @Override
    public void mouseEntered( MouseEvent arg0 )
    {
    }

    @Override
    public void mouseExited( MouseEvent arg0 )
    {
    }

    @Override
    public void mousePressed( MouseEvent e )
    {
        if ( e.isPopupTrigger() )
            doPop( e );
    }

    @Override
    public void mouseReleased( MouseEvent e )
    {
        if ( e.isPopupTrigger() )
            doPop( e );
    }

    private void doPop( MouseEvent e )
    {
        EntityPopupMenu menu = new EntityPopupMenu();
        menu.show( e.getComponent(), e.getX(), e.getY() );
    }

    /**
     * @return the paddedSize
     */
    public Dimension getPaddedSize()
    {
        return paddedSize;
    }

    @Override
    public Dimension getSize()
    {
        return size;
    }

    private class EntityPopupMenu extends JPopupMenu
    {
        private static final long serialVersionUID = 1L;

        private EntityPopupMenu()
        {
            EntityCursor cursor = getView().cursor( getEntityDef() );

            JMenuItem item = new JMenuItem( "Set to previous" );
            item.addActionListener( new SetCursorAction( CursorPosition.PREV ) );
            add( item );
            if ( cursor.isNull() || ! cursor.hasPrev() )
                item.setEnabled( false );

            item = new JMenuItem( "Set to next" );
            item.addActionListener( new SetCursorAction( CursorPosition.NEXT ) );
            add( item );
            if ( cursor.isNull() || ! cursor.hasNext() )
                item.setEnabled( false );

            item = new JMenuItem( "Set to subobject" );
            item.addActionListener( new SubobjectMenuListener() );
            add( item );
            if ( ! getEntityDef().isRecursive() )
                item.setEnabled( false );
            
            item = new JMenuItem( "Reset subobject to parent" );
            item.addActionListener( new ResetSubobjectMenuListener() );
            add( item );
            if ( ! getEntityDef().isRecursive() )
                item.setEnabled( false );
            
            item = new JMenuItem( "Load LazyLoad entities" );
            item.addActionListener( new LazyLoadEntityMenuListener() );
            add( item );
            if ( ! getEntityDef().getLazyLoadConfig().isLazyLoad() )
                item.setEnabled( false );
        }
    }

    private class SetCursorAction extends AbstractAction
    {
        private static final long serialVersionUID = 1L;
        private final CursorPosition cursorMovement;
        
        public SetCursorAction( CursorPosition cursorMovement )
        {
            super();
            this.cursorMovement = cursorMovement;
        }


        @Override
        public void actionPerformed( ActionEvent arg0 )
        {
            EntityDef entityDef = getEntityDef();
            EntityCursor cursor = getView().cursor( entityDef );
            switch ( cursorMovement )
            {
                case FIRST: cursor.setFirst(); break;
                case PREV: cursor.setPrev(); break;
                case NEXT: cursor.setNext(); break;
                case LAST: cursor.setLast(); break;
                default: throw new ZeidonException( "Unsupported cursor movement" );
            }
            
            oiDisplay.setSelectedEntity( EntitySquare.this ); // Resets attribute values.
            oiDisplay.repaint();
        }
    }

    private class ChangeSelectedEntityDefAction extends AbstractAction
    {
        private static final long serialVersionUID = 1L;
        
        private final int direction;

        public ChangeSelectedEntityDefAction(int direction)
        {
            super();
            this.direction = direction;
        }

        @Override
        public void actionPerformed( ActionEvent arg0 )
        {
            EntityDef entityDef = getEntityDef();
            if ( entityDef == null )
                entityDef = getView().getLodDef().getRoot();
            else
            {
                switch ( direction )
                {
                    case 1: entityDef = entityDef.getParent(); break;
                    case 2: entityDef = entityDef.getNextHier(); break;
                    case 3:
                    {
                        int level = entityDef.getLevel();
                        entityDef = entityDef.getPrevHier();
                        while ( entityDef != null && entityDef.getLevel() != level )
                            entityDef = entityDef.getPrevHier();
                        
                        break;
                    }
                    case 4:
                    {
                        int level = entityDef.getLevel();
                        entityDef = entityDef.getNextHier();
                        while ( entityDef != null && entityDef.getLevel() != level )
                            entityDef = entityDef.getNextHier();
                        
                        break;
                    }
                    
                    default: throw new ZeidonException( "Unsupported direction" );
                }
                
                if ( entityDef == null )
                    return;
            }

            // Did selection change?
            if ( entityDef == getEntityDef() )
                return; 
            
            env.getOiDisplay().setSelectedEntity( entityDef ).requestFocus();
        }
    }

    private class SubobjectMenuListener extends AbstractAction
    {
        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed( ActionEvent arg0 )
        {
            EntityCursor cursor = getView().cursor( getEntityDef() );
            cursor.setToSubobject();
            oiDisplay.repaint();
        }
    }

    private class ResetSubobjectMenuListener extends AbstractAction
    {
        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed( ActionEvent arg0 )
        {
            EntityCursor cursor = getView().cursor( getEntityDef() );
            cursor.resetSubobjectToParent();
            oiDisplay.repaint();
        }
    }

    private class LazyLoadEntityMenuListener extends AbstractAction
    {
        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed( ActionEvent arg0 )
        {
            getView().setLazyLoad( true );
            getView().cursor( getEntityDef() ).setFirst();
            getView().setLazyLoad( false );
            oiDisplay.repaint();
        }
    }
}
