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
package com.quinsoft.zeidon.jconsole;

import java.awt.Container;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.swing.JFrame;

import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.jmx.JmxObjectEngineMonitorMBean;
import com.quinsoft.zeidon.objectbrowser.AttributePanel;
import com.quinsoft.zeidon.objectbrowser.BrowserEnvironment;
import com.quinsoft.zeidon.objectbrowser.BrowserTask;
import com.quinsoft.zeidon.objectbrowser.BrowserView;
import com.quinsoft.zeidon.utils.JoeUtils;

public class JConsoleEnvironment extends BrowserEnvironment
{
    private   static final String BROWSER_SESSION_FILE   = "JconsoleBrowserState.xml";

    private final MBeanServerConnection server;
    private List<OeProxy> proxies;
    private OeProxy currentlySelectedOe;
    private JFrame containingJFrame;

    public JConsoleEnvironment( ObjectEngine oe, MBeanServerConnection server )
    {
        super( oe );
        this.server = server;
    }

    void searchForObjectEngineBeans()
    {
        try
        {
            proxies = new ArrayList<>();
            Set<ObjectName> objects = server.queryNames( null, null );
            for ( ObjectName object : objects )
            {
                ObjectInstance oi = server.getObjectInstance( object );
                if ( oi.getClassName().equals( "com.quinsoft.zeidon.jmx.JmxObjectEngineMonitor" ) )
                {
                    String name = object.getCanonicalName();
                    JmxObjectEngineMonitorMBean proxy =
                            JMX.newMBeanProxy(server, object, JmxObjectEngineMonitorMBean.class, true);
                    proxies.add( new OeProxy( name, proxy ) );
                }
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

    private JFrame getContainingJFrame()
    {
        if ( containingJFrame == null )
        {
            AttributePanel panel = getAttributePanel();
            for ( Container parent = panel.getParent();
                  parent != null;
                  parent = parent.getParent() )
            {
                if ( parent instanceof JFrame )
                {
                    containingJFrame = (JFrame) parent;
                    break;
                }
            }
        }

        return containingJFrame;
    }
    @Override
    public void saveEnvironment()
    {
        JFrame jframe = getContainingJFrame();
        save( jframe, BROWSER_SESSION_FILE );
    }

    @Override
    public Map<String, BrowserTask> refreshBrowserTaskList()
    {
        if ( currentlySelectedOe == null )
        {
            Map<String, BrowserTask> map = Collections.emptyMap();
            setCurrentTaskList( map );
            return map;
        }

        JmxObjectEngineMonitorMBean proxy = currentlySelectedOe.proxy;

        Map<String, BrowserTask> map = new HashMap<>();
        for ( String taskInfo : proxy.getTaskList() )
        {
            String[] strings = taskInfo.split( "," );
            map.put( strings[0], new BrowserTask( strings[0], strings[1] ) );
        }

        setCurrentTaskList( map );
        return map;
    }

    @Override
    public List<BrowserView> refreshBrowserViewList( BrowserTask task )
    {
        if ( currentlySelectedOe == null )
        {
            List<BrowserView> list = Collections.emptyList();
            setCurrentViewList( list );
            return list;
        }

        JmxObjectEngineMonitorMBean proxy = currentlySelectedOe.proxy;
        Collection<String> list = proxy.getViewList( task.taskId );
        List<BrowserView> returnList = new ArrayList<>();
        for ( String vstr : list )
        {
            String[] strings = vstr.split( ",", 5 );
            String viewName = BrowserEnvironment.UNNAMED_VIEW;

            // Was a view named passed?
            if ( strings.length == 5 && ! StringUtils.isBlank( strings[4] ) )
                viewName = strings[4]; // Yes.
            else
            if ( ! isShowUnnamedViews() ) // No.  Check to see if we should show it.
                continue; // Nope.

            BrowserView bv = new BrowserView( task,
                                              Long.parseLong( strings[0] ),
                                              Long.parseLong( strings[1] ),
                                              viewName,
                                              strings[2],
                                              strings[3]);
            returnList.add( bv );
        }

        setCurrentViewList( returnList );
        return returnList;
    }

    @Override
    public View getView( BrowserView v )
    {
        JmxObjectEngineMonitorMBean proxy = currentlySelectedOe.proxy;
        String json = proxy.getSerializedView( v.task.taskId, v.viewId );
        if ( json.startsWith( "NO " ) )
        {
            JoeUtils.sysMessageBox( "Error Retrieving View", "Error retrieving view: " + json +
                                    ".  The task or the view is no longer available on the server" );
            return null;
        }

        Application app;
        try
        {
            app = getOe().getApplication( v.appName );
        }
        catch ( ZeidonException e )
        {
            JoeUtils.sysMessageBox( "Error Loading Application", "There was an error loading application '" +
                                    v.appName + "'.  Do you have the application .jar in the jconsole classpath?" );
            return null;
        }

        return getOe().getSystemTask()
                      .deserializeOi()
                      .setApplication( app )
                      .setLodDef( v.lodName )
                      .fromString( json )
                      .asJson()
                      .activateFirst();
    }

    List<OeProxy> getProxies()
    {
        return proxies;
    }

    public void oeSelected( int idx )
    {
        currentlySelectedOe = proxies.get( idx );
        refreshBrowserTaskList();
        getTaskList().refresh();
    }

    @Override
    public void restoreEnvironment()
    {
        restore( getContainingJFrame(), BROWSER_SESSION_FILE );
    }

    @Override
    public void dropViewName( BrowserView view )
    {
        JmxObjectEngineMonitorMBean proxy = currentlySelectedOe.proxy;
        proxy.dropViewByName( view.task.taskId, view.viewName );
    }
}
