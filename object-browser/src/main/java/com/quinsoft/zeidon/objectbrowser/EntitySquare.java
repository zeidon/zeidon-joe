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

import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.objectdefinition.ViewAttribute;
import com.quinsoft.zeidon.objectdefinition.ViewEntity;

/**
 * @author DG
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
    private final ViewEntityLayout   viewEntityLayout;
    private final BrowserEnvironment env;
    private final Dimension          size;
    private final Dimension          paddedSize;
    private final Font font;
    
    EntitySquare( OiDisplay display, BrowserEnvironment environment, ViewEntityLayout layout )
    {
        super();
        this.env = environment;
        oiDisplay = display;
        viewEntityLayout = layout;
        size = new Dimension( SMALLEST_WIDTH * env.getPainterScaleFactor(), SMALLEST_HEIGHT * env.getPainterScaleFactor() );
        paddedSize = new Dimension( ( SMALLEST_WIDTH + HORIZONTAL_PAD * 2 ) * env.getPainterScaleFactor(), 
                                    ( SMALLEST_HEIGHT + VERTICAL_PAD * 2 ) * env.getPainterScaleFactor() );
        setSize( size );
        font = new Font( Font.SANS_SERIF, Font.PLAIN, env.getPainterScaleFactor() );
        setCursor( new Cursor( Cursor.DEFAULT_CURSOR ) );
        addMouseListener( this );
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
    
    static String getKeyString( EntityInstance ei, ViewEntity viewEntity )
    {
        StringBuilder builder = new StringBuilder();
        List<ViewAttribute> keys = viewEntity.getKeys();
        for ( ViewAttribute key : keys )
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
    
    ViewEntity getViewEntity()
    {
        return viewEntityLayout.getViewEntity();
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
        ViewEntity viewEntity = getViewEntity();
        EntityCursor cursor = getView().cursor( viewEntity );
        
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
        paintCenteredText( graphics2, env.getPainterScaleFactor(),viewEntity.getName(), null );
        
        switch ( cursor.getStatus() )
        {
            case NULL:
                // Do nothing.
                break;
                
            case OUT_OF_SCOPE:
                paintCenteredText( graphics2, size.height / 2, "Out of Scope", Color.WHITE );
                break;
                
            default:
                String s = getKeyString( cursor, viewEntity );
                paintCenteredText( graphics2, size.height / 2, s, null );

                s = getSiblingCount( cursor );
                paintCenteredText( graphics2, size.height - env.getPainterScaleFactor(), s, null );
        }
    }

    @Override
    public void mouseClicked( MouseEvent arg0 )
    {
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

    public Dimension getSize()
    {
        return size;
    }
    
    private class EntityPopupMenu extends JPopupMenu
    {
        private static final long serialVersionUID = 1L;
        
        public EntityPopupMenu()
        {
            JMenuItem item = new JMenuItem( "Set to subobject" );
            item.addActionListener( new SubobjectMenuListener() );
            add( item );

            item = new JMenuItem( "Reset subobject to parent" );
            item.addActionListener( new ResetSubobjectMenuListener() );
            add( item );
        }
    }
    
    private class SubobjectMenuListener extends AbstractAction
    {
        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed( ActionEvent arg0 )
        {
            EntityCursor cursor = getView().cursor( getViewEntity() );
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
            EntityCursor cursor = getView().cursor( getViewEntity() );
            cursor.resetSubobjectToParent();
            oiDisplay.repaint();
        }
    }
}
