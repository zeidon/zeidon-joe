/**
 *
 */
package com.quinsoft.zeidon.jconsole;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.management.MBeanServerConnection;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import com.sun.tools.jconsole.JConsolePlugin;

/**
 * @author dgc
 *
 */
public class JConsoleBrowserPlugin extends JConsolePlugin implements PropertyChangeListener
{
    private JConsoleBrowser            jbrowser = null;
    private Map<String, JPanel> tabs = null;

    /**
     *
     */
    public JConsoleBrowserPlugin()
    {
    }

    @Override
    public void propertyChange( PropertyChangeEvent changeEvent )
    {
    }

    @Override
    public Map<String, JPanel> getTabs()
    {
        if ( tabs == null )
        {
            MBeanServerConnection server = getContext().getMBeanServerConnection();
            jbrowser = new JConsoleBrowser( server );
            // use LinkedHashMap if you want a predictable order
            // of the tabs to be added in JConsole
            tabs = new LinkedHashMap<String, JPanel>();
            tabs.put( "Zeidon", jbrowser );
        }
        return tabs;
    }

    /*
     * Returns a SwingWorker which is responsible for updating the JTop tab.
     */
    @Override
    public SwingWorker<?, ?> newSwingWorker()
    {
        return jbrowser.newSwingWorker();
    }
}
