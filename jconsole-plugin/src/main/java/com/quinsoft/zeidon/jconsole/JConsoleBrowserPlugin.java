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
