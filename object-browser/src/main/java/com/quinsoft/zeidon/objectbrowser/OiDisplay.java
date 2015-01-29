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
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Line2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.EntityCursor.CursorStatus;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.objectdefinition.LodDef;

/**
 * This is the graphical panel that paints the OI display.
 *
 * @author DG
 *
 */
class OiDisplay extends JPanel
{
    interface EntitySelectedListener
    {
        void entitySelected( EntityDef entityDef, EntityInstance ei );
        void scaleChanged(View view, Point newCorner);
    }

    private static final long serialVersionUID = 1L;
    private static final int  TOP_PADDING = 2;

    private final BrowserEnvironment env;
    private final DrawingPane  drawingPane;
    private final LodDef       lodDef;
    private final View         view;
    private       LodDefLayout lodDefLayout;
    private final Map<EntityDef, EntitySquare> entities;
    private       EntitySquare selectedEntity;
    private final JScrollPane scroller;
    private final EntitySelectedListener entitySelectedListener;
    private       Point mousePoint;

    /**
     * @param env
     */
    OiDisplay( BrowserEnvironment env, View view, Component parent, EntitySelectedListener listener )
    {
        super( new BorderLayout() );
        this.env = env;
        this.lodDef = view.getLodDef();
        this.view = view;
        this.entitySelectedListener = listener;

        entities = new HashMap<EntityDef, EntitySquare>();
        drawingPane = new DrawingPane();
        drawingPane.setCursor( new Cursor( Cursor.MOVE_CURSOR ) );
        MouseHandler handler = new MouseHandler();
        drawingPane.addMouseMotionListener( handler );
        drawingPane.addMouseListener( handler );
        drawingPane.addMouseWheelListener( handler );

        scroller = new JScrollPane(drawingPane);
        add(scroller, BorderLayout.CENTER);
        scroller.setVisible( true );
        setup( view, parent );
        setVisible( true );
    }

    private void setup( View view, Component parent )
    {
        EntityDef root = lodDef.getRoot();
        lodDefLayout = env.getOdLayout( lodDef );

        EntitySquare e = new EntitySquare( this, env, null ); // Create a dummy just to get the size.
        Dimension size = new Dimension( lodDefLayout.getWidth() * e.getPaddedSize().width,
                                        lodDefLayout.getHeight() * e.getPaddedSize().height );
        drawingPane.setPreferredSize( size );
        drawingPane.setVisible( true );

        e = addEntity( root, 0, size.width );
        setSelectedEntity( e );

        if ( scroller.getWidth() < size.width )
        {
            int mid = size.width / 2 - parent.getWidth() / 2 + 10; // 10 is for width of scroll bar.
            Point p = new Point( mid, 0 );
            scroller.getViewport().setViewPosition( p );
        }
    }

    private EntitySquare addEntity( EntityDef entityDef, int left, int right )
    {
        EntityDefLayout layout = lodDefLayout.getEntityDefLayout( entityDef );
        EntitySquare e = new EntitySquare( this, env, layout );

        int totalWidth = right - left - e.getWidth();
        int middle = totalWidth / 2;
        int topPadding = TOP_PADDING * env.getPainterScaleFactor();

        e.setBounds( left + middle, ( entityDef.getDepth() - 1 ) * e.getPaddedSize().height + topPadding,
                     e.getWidth(), e.getHeight() );
        drawingPane.add(  e );
        entities.put( entityDef, e );

        if ( entityDef.getChildCount() == 0 )
            return e;

        List<EntityDef> children = entityDef.getChildren();
        int newLeft = left;
        for ( EntityDef child : children )
        {
            layout = lodDefLayout.getEntityDefLayout( child );
            int w = layout.getWidth() * e.getPaddedSize().width;
            addEntity( child, newLeft, newLeft + w );
            newLeft += w;
        }

        return e;
    }

    /**
     * @return the selectedEntity
     */
    EntitySquare getSelectedEntity()
    {
        return selectedEntity;
    }

    void setSelectedEntityFromEntityDef( EntityDef entityDef )
    {
        EntitySquare prevSelected = getSelectedEntity();

        EntitySquare square = entities.get( entityDef );
        setSelectedEntity( square );
        prevSelected.repaint();
        square.repaint();

        // Check to see if the square is outside the viewable area on the scroll.
        Rectangle sqrec = square.getBounds();
        Rectangle scrollrec = scroller.getViewport().getViewRect();
        if ( ! scrollrec.contains( sqrec ) )
        {
            // Create a point that puts the middle of the square into the middle
            // of the scroll.
            Point p = new Point( (int) ( sqrec.getCenterX() - scrollrec.getWidth() / 2 ),
                                 (int) ( sqrec.getCenterY() + scrollrec.getHeight() / 2 ) );
            repositionScroll( p );
        }

        revalidate();
    }

    /**
     * @param selectedEntity the selectedEntity to set
     */
    void setSelectedEntity( EntitySquare selectedEntity )
    {
        this.selectedEntity = selectedEntity;
        EntityDef entityDef = selectedEntity.getEntityDef();
        EntityCursor cursor = view.cursor( entityDef );
        if ( cursor.getStatus() == CursorStatus.SET )
            entitySelectedListener.entitySelected( entityDef, cursor.getEntityInstance() );
        else
            entitySelectedListener.entitySelected( entityDef, null );
    }

    /**
     * @return the view
     */
    View getView()
    {
        return view;
    }

    class DrawingPane extends JPanel
    {
        private static final long serialVersionUID = 1L;

        public DrawingPane()
        {
            super( null );
        }

        @Override
        protected void paintComponent( Graphics g )
        {
            super.paintComponent( g );

            // Now draw the lines.
            Graphics2D g2 = (Graphics2D) g;

            // Set a nice thick black line.
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setPaint(Color.black);

            for ( EntityDef entityDef : lodDef.getViewEntitiesHier() )
            {
                EntityDef parent = entityDef.getParent();
                if ( parent == null )  // If this entity is the root we don't need to draw a line.
                    continue;

                EntitySquare e = entities.get( entityDef );
                EntitySquare p = entities.get( parent );

                g2.draw(new Line2D.Double(p.getBottomAnchor(), e.getTopAnchor()));
            }
        }
    }

    void repositionScroll( Point p )
    {
        // Make sure we aren't scrolling off the screen
        Dimension s = getSize();
        Dimension ps = drawingPane.getPreferredSize();
        p.x = Math.min( ps.width - s.width, p.x );
        p.y = Math.min( ps.height - s.height, p.y );

        p.x = Math.max( 0, p.x );
        p.y = Math.max( 0, p.y );

        scroller.getViewport().setViewPosition( p );
        scroller.revalidate();
    }

    private class MouseHandler extends MouseAdapter
    {
        @Override
        public void mouseDragged( MouseEvent e )
        {
            int x = mousePoint.x - e.getX();
            int y = mousePoint.y - e.getY();

            final Point p = scroller.getViewport().getViewPosition();
            p.x += x;
            p.y += y;
            javax.swing.SwingUtilities.invokeLater( new Runnable()
            {
                @Override
                public void run()
                {
                    repositionScroll( p );
                }
            } );
        }

        @Override
        public void mousePressed( MouseEvent e )
        {
            mousePoint = new Point( e.getX(), e.getY() );
        }

        private void changeScale( MouseEvent e, int scale )
        {
            int oldScale = env.getPainterScaleFactor();
            if ( env.setPainterScaleFactor( scale ) )
            {
                // The scale factor has bounds so we need to get it again.
                scale = env.getPainterScaleFactor();

                // We want the center of the scroll pane to remain in the middle once we resize.
                Point corner = scroller.getViewport().getViewPosition();
                int width = e.getX() - corner.x;
                int x = ( corner.x + width ) * scale / oldScale - width;
                int height = e.getY() - corner.y;
                int y = ( corner.y + height ) * scale / oldScale - height;
                Point newCorner = new Point( x, y );

                entitySelectedListener.scaleChanged( view, newCorner );
            }

        }

        @Override
        public void mouseWheelMoved( MouseWheelEvent e )
        {
            int scale = env.getPainterScaleFactor() + - e.getWheelRotation();
            changeScale( e, scale );
        }

        @Override
        public void mouseClicked( MouseEvent e )
        {
            if ( e.getClickCount() == 2 )
            {
                if ( env.getPainterScaleFactor() == env.getDefaultPainterScale() )
                    changeScale( e, 1 );
                else
                    changeScale( e, env.getDefaultPainterScale() );
            }
        }
    }
}
