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

import static java.lang.management.ManagementFactory.THREAD_MXBEAN_NAME;
import static java.lang.management.ManagementFactory.newPlatformMXBeanProxy;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.net.MalformedURLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * JTop is a JPanel to display thread's name, CPU time, and its state in a
 * table.
 */
public class JTop extends JPanel
{
    private static final long     serialVersionUID = -1499762160973870696L;
    private MBeanServerConnection server;
    private ThreadMXBean          tmbean;
    private MyTableModel          tmodel;

    public JTop()
    {
        super( new GridLayout( 1, 0 ) );

        tmodel = new MyTableModel();
        JTable table = new JTable( tmodel );
        table.setPreferredScrollableViewportSize( new Dimension( 500, 300 ) );

        // Set the renderer to format Double
        table.setDefaultRenderer( Double.class, new DoubleRenderer() );
        // Add some space
        table.setIntercellSpacing( new Dimension( 6, 3 ) );
        table.setRowHeight( table.getRowHeight() + 4 );

        // Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane( table );

        // Add the scroll pane to this panel.
        add( scrollPane );
    }

    // Set the MBeanServerConnection object for communicating
    // with the target VM
    public void setMBeanServerConnection( MBeanServerConnection mbs )
    {
        this.server = mbs;
        try
        {
            this.tmbean = newPlatformMXBeanProxy( server, THREAD_MXBEAN_NAME, ThreadMXBean.class );
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
        if ( !tmbean.isThreadCpuTimeSupported() )
        {
            System.err.println( "This VM does not support thread CPU time monitoring" );
        }
        else
        {
            tmbean.setThreadCpuTimeEnabled( true );
        }
    }

    class MyTableModel extends AbstractTableModel
    {
        private static final long                 serialVersionUID = -7877310288576779514L;
        private String[]                          columnNames      = { "ThreadName", "CPU(sec)", "State" };
        // List of all threads. The key of each entry is the CPU time
        // and its value is the ThreadInfo object with no stack trace.
        private List<Map.Entry<Long, ThreadInfo>> threadList       = Collections.emptyList();

        public MyTableModel()
        {
        }

        @Override
        public int getColumnCount()
        {
            return columnNames.length;
        }

        @Override
        public int getRowCount()
        {
            return threadList.size();
        }

        @Override
        public String getColumnName( int col )
        {
            return columnNames[ col ];
        }

        @Override
        public Object getValueAt( int row, int col )
        {
            Map.Entry<Long, ThreadInfo> me = threadList.get( row );
            switch ( col )
            {
                case 0:
                    // Column 0 shows the thread name
                    return me.getValue().getThreadName();
                case 1:
                    // Column 1 shows the CPU usage
                    long ns = me.getKey().longValue();
                    double sec = ns / 1000000000;
                    return new Double( sec );
                case 2:
                    // Column 2 shows the thread state
                    return me.getValue().getThreadState();
                default:
                    return null;
            }
        }

        @Override
        public Class<?> getColumnClass( int c )
        {
            return getValueAt( 0, c ).getClass();
        }

        void setThreadList( List<Map.Entry<Long, ThreadInfo>> list )
        {
            threadList = list;
        }
    }

    /**
     * Get the thread list with CPU consumption and the ThreadInfo for each
     * thread sorted by the CPU time.
     */
    private List<Map.Entry<Long, ThreadInfo>> getThreadList()
    {
        // Get all threads and their ThreadInfo objects
        // with no stack trace
        long[] tids = tmbean.getAllThreadIds();
        ThreadInfo[] tinfos = tmbean.getThreadInfo( tids );

        // build a map with key = CPU time and value = ThreadInfo
        SortedMap<Long, ThreadInfo> map = new TreeMap<Long, ThreadInfo>();
        for ( int i = 0; i < tids.length; i++ )
        {
            long cpuTime = tmbean.getThreadCpuTime( tids[ i ] );
            // filter out threads that have been terminated
            if ( cpuTime != -1 && tinfos[ i ] != null )
            {
                map.put( new Long( cpuTime ), tinfos[ i ] );
            }
        }

        // build the thread list and sort it with CPU time
        // in decreasing order
        Set<Map.Entry<Long, ThreadInfo>> set = map.entrySet();
        List<Map.Entry<Long, ThreadInfo>> list = new ArrayList<Map.Entry<Long, ThreadInfo>>( set );
        Collections.reverse( list );
        return list;
    }

    /**
     * Format Double with 4 fraction digits
     */
    class DoubleRenderer extends DefaultTableCellRenderer
    {
        private static final long serialVersionUID = 1704639497162584382L;
        NumberFormat              formatter;

        public DoubleRenderer()
        {
            super();
            setHorizontalAlignment( JLabel.RIGHT );
        }

        @Override
        public void setValue( Object value )
        {
            if ( formatter == null )
            {
                formatter = NumberFormat.getInstance();
                formatter.setMinimumFractionDigits( 4 );
            }
            setText( ( value == null ) ? ""
                    : formatter.format( value ) );
        }
    }

    // SwingWorker responsible for updating the GUI
    //
    // It first gets the thread and CPU usage information as a
    // background task done by a worker thread so that
    // it will not block the event dispatcher thread.
    //
    // When the worker thread finishes, the event dispatcher
    // thread will invoke the done() method which will update
    // the UI.
    class Worker extends SwingWorker<List<Map.Entry<Long, ThreadInfo>>, Object>
    {
        private MyTableModel tmodel;

        Worker( MyTableModel tmodel )
        {
            this.tmodel = tmodel;
        }

        // Get the current thread info and CPU time
        @Override
        public List<Map.Entry<Long, ThreadInfo>> doInBackground()
        {
            return getThreadList();
        }

        // fire table data changed to trigger GUI update
        // when doInBackground() is finished
        @Override
        protected void done()
        {
            try
            {
                // Set table model with the new thread list
                tmodel.setThreadList( get() );
                // refresh the table model
                tmodel.fireTableDataChanged();
            }
            catch ( InterruptedException e )
            {
            }
            catch ( ExecutionException e )
            {
            }
        }
    }

    // Return a new SwingWorker for UI update
    public SwingWorker<?, ?> newSwingWorker()
    {
        return new Worker( tmodel );
    }

    public static void main( String[] args ) throws Exception
    {
        // Validate the input arguments
        if ( args.length != 1 )
        {
            usage();
        }

        String[] arg2 = args[ 0 ].split( ":" );
        if ( arg2.length != 2 )
        {
            usage();
        }
        String hostname = arg2[ 0 ];
        int port = -1;
        try
        {
            port = Integer.parseInt( arg2[ 1 ] );
        }
        catch ( NumberFormatException x )
        {
            usage();
        }
        if ( port < 0 )
        {
            usage();
        }

        // Create the JTop Panel
        final JTop jtop = new JTop();
        // Set up the MBeanServerConnection to the target VM
        MBeanServerConnection server = connect( hostname, port );
        jtop.setMBeanServerConnection( server );

        // A timer task to update GUI per each interval
        TimerTask timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                // Schedule the SwingWorker to update the GUI
                jtop.newSwingWorker().execute();
            }
        };

        // Create the standalone window with JTop panel
        // by the event dispatcher thread
        SwingUtilities.invokeAndWait( new Runnable()
        {
            @Override
            public void run()
            {
                createAndShowGUI( jtop );
            }
        } );

        // refresh every 2 seconds
        Timer timer = new Timer( "JTop Sampling thread" );
        timer.schedule( timerTask, 0, 2000 );

    }

    // Establish a connection with the remote application
    //
    // You can modify the urlPath to the address of the JMX agent
    // of your application if it has a different URL.
    //
    // You can also modify the following code to take
    // username and password for client authentication.
    private static MBeanServerConnection connect( String hostname, int port )
    {
        // Create an RMI connector client and connect it to
        // the RMI connector server
        String urlPath = "/jndi/rmi://" + hostname + ":" + port + "/jmxrmi";
        MBeanServerConnection server = null;
        try
        {
            JMXServiceURL url = new JMXServiceURL( "rmi", "", 0, urlPath );
            JMXConnector jmxc = JMXConnectorFactory.connect( url );
            server = jmxc.getMBeanServerConnection();
        }
        catch ( MalformedURLException e )
        {
            // should not reach here
        }
        catch ( IOException e )
        {
            System.err.println( "\nCommunication error: " + e.getMessage() );
            System.exit( 1 );
        }
        return server;
    }

    private static void usage()
    {
        System.out.println( "Usage: java JTop <hostname>:<port>" );
        System.exit( 1 );
    }

    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event-dispatching thread.
     */
    private static void createAndShowGUI( JPanel jtop )
    {
        // Create and set up the window.
        JFrame frame = new JFrame( "JTop" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        // Create and set up the content pane.
        JComponent contentPane = (JComponent) frame.getContentPane();
        contentPane.add( jtop, BorderLayout.CENTER );
        contentPane.setOpaque( true ); // content panes must be opaque
        contentPane.setBorder( new EmptyBorder( 12, 12, 12, 12 ) );
        frame.setContentPane( contentPane );

        // Display the window.
        frame.pack();
        frame.setVisible( true );
    }

}