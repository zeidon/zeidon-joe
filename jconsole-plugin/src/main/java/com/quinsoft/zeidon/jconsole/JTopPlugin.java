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