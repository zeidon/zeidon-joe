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
package com.quinsoft.zeidon.jconsole;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.SwingWorker;

import com.sun.tools.jconsole.JConsoleContext;
import com.sun.tools.jconsole.JConsoleContext.ConnectionState;
import com.sun.tools.jconsole.JConsolePlugin;

/**
 * JTopPlugin is a subclass to com.sun.tools.jconsole.JConsolePlugin
 *
 * JTopPlugin is loaded and instantiated by JConsole. One instance is created
 * for each window that JConsole creates. It listens to the connected property
 * change so that it will update JTop with the valid MBeanServerConnection
 * object. JTop is a JPanel object displaying the thread and its CPU usage
 * information.
 */
public class JTopPlugin extends JConsolePlugin implements PropertyChangeListener
{
    private JTop                jtop = null;
    private Map<String, JPanel> tabs = null;

    public JTopPlugin()
    {
        // register itself as a listener
        addContextPropertyChangeListener( this );
    }

    /*
     * Returns a JTop tab to be added in JConsole.
     */
    @Override
    public synchronized Map<String, JPanel> getTabs()
    {
        if ( tabs == null )
        {
            jtop = new JTop();
            jtop.setMBeanServerConnection( getContext().getMBeanServerConnection() );
            // use LinkedHashMap if you want a predictable order
            // of the tabs to be added in JConsole
            tabs = new LinkedHashMap<String, JPanel>();
            tabs.put( "JTop", jtop );
        }
        return tabs;
    }

    /*
     * Returns a SwingWorker which is responsible for updating the JTop tab.
     */
    @Override
    public SwingWorker<?, ?> newSwingWorker()
    {
        return jtop.newSwingWorker();
    }

    // You can implement the dispose() method if you need to release
    // any resource when the plugin instance is disposed when the JConsole
    // window is closed.
    //
    // public void dispose() {
    // }

    /*
     * Property listener to reset the MBeanServerConnection at reconnection
     * time.
     */
    @Override
    public void propertyChange( PropertyChangeEvent ev )
    {
        String prop = ev.getPropertyName();
        if ( prop == JConsoleContext.CONNECTION_STATE_PROPERTY )
        {
            ConnectionState newState = (ConnectionState) ev.getNewValue();
            // JConsole supports disconnection and reconnection
            // The MBeanServerConnection will become invalid when
            // disconnected. Need to use the new MBeanServerConnection object
            // created at reconnection time.
            if ( newState == ConnectionState.CONNECTED && jtop != null )
            {
                jtop.setMBeanServerConnection( getContext().getMBeanServerConnection() );
            }
        }
    }
}