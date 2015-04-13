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

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.ToolTipManager;

import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.objectdefinition.LodDef;

/**
 * This object contains info about the browser environment that can be passed around
 * to the different components.
 *
 * @author DG
 *
 */
public abstract class BrowserEnvironment
{
    protected static final String UNNAMED_VIEW = "*** unnamed ***";

    private final ObjectEngine oe;
    private final Map<LodDef, LodDefLayout> odLayouts;

    private int     painterScaleFactor = 8;
    private boolean showHiddenAttributes = false;
    private boolean showNullAttributes   = true;
    private boolean showUnnamedViews     = true;
    private OiDisplayPanel oiDisplayPanel;
    private TaskList taskList;
    private ViewListTable viewList;
    private AttributePanel attributePanel;
    private List<BrowserView> currentViewList;
    private Map<String, BrowserTask> currentTaskList;
    private TwinInstancesPanel twinInstancesPanel;
    private EntityListPanel entityListPanel;
    private EntityDisplayAttributes entityDisplayAttributes;

    /**
     * @param oe
     * @param objectBrowser
     */
    public BrowserEnvironment( ObjectEngine oe )
    {
        super();
        this.oe = oe;
        odLayouts = new HashMap<LodDef, LodDefLayout>();
        entityDisplayAttributes = new EntityDisplayAttributes( getBrowserDir() );
        ToolTipManager.sharedInstance().setInitialDelay( 0 );
        ToolTipManager.sharedInstance().setDismissDelay( 9999999 );
        ToolTipManager.sharedInstance().setReshowDelay( 0 );
    }

    protected ObjectEngine getOe()
    {
        return oe;
    }

    LodDefLayout getOdLayout( View view )
    {
        return getOdLayout( view.getLodDef() );
    }

    synchronized LodDefLayout getOdLayout( LodDef lodDef )
    {
        LodDefLayout layout = odLayouts.get( lodDef );
        if ( layout == null )
        {
            layout = new LodDefLayout( this, lodDef );
            odLayouts.put( lodDef, layout );
        }

        return layout;
    }

    /**
     * @return the painterScaleFactor
     */
    int getPainterScaleFactor()
    {
        return painterScaleFactor;
    }

    int getDefaultPainterScale()
    {
        return 8;
    }

    private String getBrowserDir()
    {
        String filename = System.getProperty("user.home") + "/.ZeidonBrowser/";
        createBrowserDir( filename );
        return filename;
    }

    private void createBrowserDir( String dir )
    {
        File theDir = new File( dir );

        // if the directory does not exist, create it
        if ( theDir.exists() )
            return;

        try
        {
            theDir.mkdir();
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e ).prependFilename( dir );
        }
    }

    public BrowserEnvironment restore( Component component, String filename )
    {
        String f =  getBrowserDir() + filename;
        WindowBoundsRestorer restorer = new WindowBoundsRestorer( f );
        restorer.restore( component );
        return this;
    }

    public BrowserEnvironment save( Component component, String filename )
    {
        String f =  getBrowserDir() + filename;
        WindowBoundsRestorer restorer = new WindowBoundsRestorer( f );
        restorer.save( component );
        return this;
    }

    /**
     * @param painterScaleFactor the painterScaleFactor to set
     */
    boolean setPainterScaleFactor( int painterScaleFactor )
    {
        // Make sure new factor is between 1 and 15.
        int n = Math.min( 15, Math.max( 1, painterScaleFactor ) );
        if ( n == this.painterScaleFactor )
            return false;

        this.painterScaleFactor = n;
        return true;
    }

    void warn( String format, Object...args )
    {
        oe.getSystemTask().log().warn( format, args );
    }

    void error( String format, Object...args )
    {
        oe.getSystemTask().log().error( format, args );
    }

    /**
     * @return the showHiddenAttributes
     */
    public boolean isShowHiddenAttributes()
    {
        return showHiddenAttributes;
    }

    /**
     * @param showHiddenAttributes the showHiddenAttributes to set
     */
    public void setShowHiddenAttributes( boolean showHiddenAttributes )
    {
        this.showHiddenAttributes = showHiddenAttributes;
    }

    /**
     * @return the showNullAttributes
     */
    public boolean isShowNullAttributes()
    {
        return showNullAttributes;
    }

    /**
     * @param showNullAttributes the showNullAttributes to set
     */
    public void setShowNullAttributes( boolean showNullAttributes )
    {
        this.showNullAttributes = showNullAttributes;
    }

    public void setShowUnnamedViews(boolean showUnnamedViews)
    {
        this.showUnnamedViews = showUnnamedViews;
    }

    public boolean isShowUnnamedViews()
    {
        return showUnnamedViews;
    }

    public OiDisplayPanel createOiDisplay( Container container )
    {
        oiDisplayPanel = new OiDisplayPanel( this );
        return oiDisplayPanel;
    }

    public ViewListTable getViewList()
    {
        return viewList;
    }

    public void setViewList( ViewListTable viewList )
    {
        this.viewList = viewList;
    }

    public void viewSelected( final BrowserView view )
    {
        // Use invokeLater otherwise toFront() won't always work.
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                View v = getView( view );
                oiDisplayPanel.displayView( v );
                entityListPanel.setView( v );
                oiDisplayPanel.setFocusToDisplay();
            }
        });
    }

    public AttributePanel createAttributePanel()
    {
        attributePanel = new AttributePanel( this );
        return attributePanel;
    }

    public AttributePanel getAttributePanel()
    {
        return attributePanel;
    }

    public void entitySelected( EntityDef entityDef, EntityInstance ei )
    {
        attributePanel.setEntity( entityDef, ei );
        twinInstancesPanel.setEntityInstance( ei );
        entityListPanel.setEntityDef( entityDef );
    }

    public abstract void restoreEnvironment();
    public abstract void restoreEnvironment( JComponent component );
    public abstract void saveEnvironment();

    public List<BrowserView> getCurrentViewList()
    {
        return currentViewList;
    }

    public void setCurrentViewList( List<BrowserView> currentViewList )
    {
        this.currentViewList = currentViewList;
    }

    public abstract Map<String, BrowserTask> refreshBrowserTaskList();
    public abstract List<BrowserView> refreshBrowserViewList( BrowserTask task );
    public abstract void dropViewName( BrowserView view );

    /**
     * Retrieve a view from a BrowserView.
     *
     * @param v
     * @return
     */
    public abstract View getView( BrowserView v );

    public BrowserTask getTaskById( String id )
    {
        return currentTaskList.get( id );
    }

    public Map<String, BrowserTask> getCurrentTaskList()
    {
        return currentTaskList;
    }

    public void setCurrentTaskList( Map<String, BrowserTask> browserTaskList )
    {
        this.currentTaskList = browserTaskList;
    }

    public TwinInstancesPanel getTwinInstancesPanel()
    {
        return twinInstancesPanel;
    }

    public void setTwinInstancesPanel( TwinInstancesPanel twinInstancesPanel )
    {
        this.twinInstancesPanel = twinInstancesPanel;
    }

    public TaskList getTaskList()
    {
        return taskList;
    }

    public void setTaskList( TaskList taskList )
    {
        this.taskList = taskList;
    }

    public EntityListPanel getEntityListPanel()
    {
        return entityListPanel;
    }

    public void setEntityListPanel( EntityListPanel entityListPanel )
    {
        this.entityListPanel = entityListPanel;
    }

    protected OiDisplayPanel getOiDisplayPanel()
    {
        return oiDisplayPanel;
    }

    public EntityDisplayAttributes getEntityDisplayAttributes()
    {
        return entityDisplayAttributes;
    }

    /**
     * Adds a hot-key action that is available from any sub-component.
     * @param viewChooser
     *
     * @param keyStroke Key-stroke string; e.g. "alt 1".
     * @param action action to call.
     */
    void addTopLevelKeyAction( JComponent childComponent, String keyStroke, Action action )
    {
        InputMap im = oiDisplayPanel.getInputMap( JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT );
        ActionMap am = oiDisplayPanel.getActionMap();
        im.put( KeyStroke.getKeyStroke( keyStroke ), action.toString() );
        am.put( action.toString(), action );

        im = childComponent.getInputMap( JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT );
        am = childComponent.getActionMap();
        im.put( KeyStroke.getKeyStroke( keyStroke ), action.toString() );
        am.put( action.toString(), action );
    }

    void setClipboardContents( String aString )
    {
        StringSelection stringSelection = new StringSelection( aString );
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents( stringSelection, oiDisplayPanel );
    }

    CopyAction createCopyAction( String value )
    {
        return new CopyAction( value );
    }

    private class CopyAction extends AbstractAction
    {
        private static final long serialVersionUID = 1L;
        private final String value;

        public CopyAction(String value)
        {
            super();
            this.value = value;
        }


        @Override
        public void actionPerformed( ActionEvent arg0 )
        {
            setClipboardContents( value );
        }
    }
}
