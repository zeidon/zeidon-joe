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

package com.quinsoft.zeidon.jmx;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.MapMaker;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.ObjectEngineEventListener;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonLogger;
import com.quinsoft.zeidon.objectdefinition.LodDef;
import com.quinsoft.zeidon.standardoe.JavaOeConfiguration;
import com.quinsoft.zeidon.utils.JoeUtils;

/**
 * An instance of an ObjectEngineEventListener that creates a JMX bean that can
 * be queried through JMX (e.g. jconsole).  The OE can then be partly managed through
 * the bean.
 *
 * @author dg
 *
 */
public class JmxObjectEngineMonitor implements JmxObjectEngineMonitorMBean, ObjectEngineEventListener
{
    private ObjectEngine oe;
    private final ConcurrentMap<String, EventInfo> eventInfo = new MapMaker().concurrencyLevel( 10 ).makeMap();

    /**
     */
    public JmxObjectEngineMonitor( JavaOeConfiguration config )
    {
        if ( ! StringUtils.isBlank( config.getJmxAppName() ) )
            JoeUtils.RegisterJmxBean( this, "com.quinsoft.zeidon:type=ObjectEngine", config.getJmxAppName() );
    }

    public JmxObjectEngineMonitor( String jmxName )
    {
        JoeUtils.RegisterJmxBean( this, jmxName, null );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.jmx.ObjectEngineMonitorMBean#getViewList()
     */
    @Override
    public Collection<Map<String, String>> getViewList()
    {
        ArrayList<Map<String,String>> list = new ArrayList<Map<String,String>>();
        if ( oe == null )
            return list;

        for ( Task task : oe.getTaskList() )
        {
            if ( ! task.isValid() )
                continue;

            for ( View v : task.getViewList() )
            {
                for ( String name : task.getViewNameList( v ) )
                {
                    HashMap<String, String> map = new HashMap<String,String>();
                    list.add( map );
                    map.put( "taskId", task.getTaskId() );
                    map.put( "application", v.getApplication().getName() );
                    map.put( "lodDef", v.getLodDef().getName() );
                    map.put( "name", name );
                    map.put( "oiId", Long.toString( v.getOiId() ) );
                }
            }
        }

        return list;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.jmx.ObjectEngineMonitorMBean#dropViewByName(java.lang.String, java.lang.String)
     */
    @Override
    public String dropViewByName( String taskId, String viewName )
    {
        if ( oe == null )
            return "ObjectEngine not specified";

        Task task = oe.getTaskById( taskId );
        if ( task == null )
            return "Unknown TaskId";

        View v = task.getViewByName( viewName );
        if ( v == null )
            return "Unknown viewname for task";

        v.dropNameForView( viewName );

        return "View name dropped";
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ObjectEngineEventListener#setObjectEngine(com.quinsoft.zeidon.ObjectEngine)
     */
    @Override
    public void setObjectEngine( ObjectEngine oe )
    {
        this.oe = oe;
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.jmx.ObjectEngineMonitorMBean#getRuntimeProperties()
     */
    @Override
    public Properties getRuntimeProperties()
    {
        Properties properties = new Properties();

        // Add activate information.
        for ( String key : eventInfo.keySet() )
        {
            EventInfo info = eventInfo.get( key );
            synchronized ( info ) // To keep other threads from changing times and counts.
            {
                if ( info.activates > 0 )
                {
                    properties.put( String.format( "%s activates", key ), info.activates );
                    double average = info.totalActivateTime / 1000.0 / info.activates;
                    properties.put( String.format( "%s activate average", key ), String.format( "%1.4f", average ) );
                    if ( info.activateErrors > 0 )
                        properties.put( String.format( "%s activate errors", key ), info.activateErrors );
                }

                if ( info.commits > 0 )
                {
                    properties.put( String.format( "%s commits\n", key ), info.commits );
                    double average = info.totalCommitTime / 1000.0 / info.commits;
                    properties.put( String.format( "%s commit average", key ), String.format( "%1.4f", average ) );
                    if ( info.commitErrors > 0 )
                        properties.put( String.format( "%s commit errors", key ), info.commitErrors );
                }
            }
        }

        ZeidonLogger logger = oe.getSystemTask().log();
        for ( Object key : properties.keySet() )
        {
            Object value = properties.get( key );
            logger.info( "%s %s", key, value );
        }

        return properties;
    }

    private EventInfo getEventInfo( String key )
    {
        EventInfo info = eventInfo.get( key );
        if ( info == null )
        {
            info = new EventInfo();
            eventInfo.putIfAbsent( key, info );
            info = eventInfo.get( key );
        }

        return info;
    }

    private EventInfo getEventInfo( View view )
    {
        LodDef lodDef = view.getLodDef();
        EventInfo info = getEventInfo( lodDef.toString() );
        if ( info.lodDef == null )
            info.lodDef = lodDef;

        return info;
    }

    private EventInfo getEventInfo( Application app )
    {
        EventInfo info = getEventInfo( app.toString() );
        return info;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ObjectEngineEventListener#objectInstanceActivated(com.quinsoft.zeidon.View, long)
     */
    @Override
    public void objectInstanceActivated( View view, View qualification, long millis, Exception exception )
    {
        try
        {
            EventInfo info = getEventInfo( view );
            info.incrementActivate( millis, exception );
            info = getEventInfo( view.getApplication() );
            info.incrementActivate( millis, exception );
        }
        catch ( Exception e )
        {
            oe.getSystemTask().log().error( e );
        }
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ObjectEngineEventListener#objectInstanceCommitted(com.quinsoft.zeidon.View, long)
     */
    @Override
    public void objectInstanceCommitted( Collection<View> viewList, long millis, Exception exception )
    {
        try
        {
            for ( View view : viewList )
            {
                if ( view == null )
                    continue;

                EventInfo info = getEventInfo( view );
                info.incrementCommit( millis, exception );
                info = getEventInfo( view.getApplication() );
                info.incrementCommit( millis, exception );
            }
        }
        catch ( Exception e )
        {
            oe.getSystemTask().log().error( e );
        }
    }

    private class EventInfo
    {
        LodDef lodDef;
        long totalActivateTime = 0;
        long activates = 0;
        long totalCommitTime = 0;
        long commits = 0;
        long activateErrors = 0;
        long commitErrors = 0;

        private synchronized void incrementActivate( long millis, Exception e )
        {
            totalActivateTime += millis;
            activates++;
            if ( e != null )
                activateErrors++;
        }

        private synchronized void incrementCommit( long millis, Exception e )
        {
            totalCommitTime += millis;
            commits++;
            if ( e != null )
                commitErrors++;
        }
    }

    @Override
    public Collection<String> getTaskList()
    {
        ArrayList<String> taskList = new ArrayList<String>();
        for ( Task task : oe.getTaskList() )
            taskList.add( task.getTaskId() + "," + task.getApplication().getName() );

        return taskList;
    }

    @Override
    public String logViews( String taskId )
    {
        if ( oe == null )
            return "ObjectEngine not specified";

        Task task = oe.getTaskById( taskId );
        if ( task == null )
            return "Unknown TaskId";

        for ( View view : task.getViewList() )
        {
            Collection<String> nameList = view.getNameList();
            task.log().info( "View %s (%s)  Count = %d  Names = %s",
                             view.getId(), view.getLodDef(), view.getEntityCount( true ), nameList );
        }

        return "Views listed in log";
    }

    @Override
    public String startObjectBrowser()
    {
        if ( oe == null )
            return "ObjectEngine not specified";

        try
        {
            oe.startBrowser();
        }
        catch ( Exception e )
        {
            oe.getSystemTask().log().error( e );
            return "Error starting browser: " + e.getMessage();
        }

        return "Browser started";
    }

    @Override
    public String dropCachedViewByName( String viewName )
    {
        if ( oe == null )
            return "ObjectEngine not specified";

        Task task = oe.getSystemTask();
        if ( task == null )
            return "No System Task!";

        View v = task.getViewByName( viewName );
        if ( v == null )
            return "Unknown viewname for System task";

        v.dropNameForView( viewName );

        return "Cached view name dropped";
    }

    @Override
    public Collection<String> getViewList( String taskId )
    {
        ArrayList<String> list = new ArrayList<>();
        if ( oe == null )
            return list;

        Task task = oe.getTaskById( taskId );
        if ( task == null  || ! task.isValid() )
            return list;

        for ( View v : task.getViewList() )
        {
            String lodDef = v.getLodDef().getName();
            String app = v.getLodDef().getApplication().getName();
            Collection<String> nameList = v.getNameList();
            if ( nameList.size() == 0 )
            {
                list.add( String.format( "%d,%d,%s,%s", v.getId(), v.getOiId(), lodDef, app ) );
            }
            else
            {
                for ( String name : nameList )
                    list.add( String.format( "%d,%d,%s,%s,%s", v.getId(), v.getOiId(), lodDef, app, name ) );
            }
        }

        return list;
    }

    @Override
    public String getSerializedView( String taskId, Long viewId )
    {
        if ( oe == null )
            return "NO OE";

        Task task = oe.getTaskById( taskId );
        if ( task == null  || ! task.isValid() )
            return "NO TASK";

        View view = task.getViewByKey( viewId );
        if ( view == null )
            return "NO VIEW";

        return view.serializeOi().asJson().withIncremental().compressed().toStringWriter().toString();
    }

    @Override
    public String logTaskList()
    {
        for ( Task task : oe.getTaskList() )
            task.log().info( "Task ID %s for %s has ~ %d views",
                             task.getTaskId(), task.getApplication().getName(), task.getViewCount() );

        return "Tasks logged";
    }

    @Override
    public String logAllTasksAndViews()
    {
        for ( Task task : oe.getTaskList() )
        {
            task.log().info( "Task ID %s for %s", task.getTaskId(), task.getApplication().getName() );
            logViews( task.getTaskId() );
        }

        return "Tasks logged";
    }
}
