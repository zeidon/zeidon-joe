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
            String viewName = "*** unnamed *** ";
            if ( strings.length == 5 && ! StringUtils.isBlank( strings[4] ) )
                viewName = strings[4];

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
                      .setViewOd( v.lodName )
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
}
