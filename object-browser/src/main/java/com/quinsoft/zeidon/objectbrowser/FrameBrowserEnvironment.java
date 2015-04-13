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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;

/**
 * @author dgc
 *
 */
public class FrameBrowserEnvironment extends BrowserEnvironment
{
    private   static final String BROWSER_SESSION_FILE   = "BrowserState.xml";

    private final ObjectBrowser objectBrowser;

    /**
     * @param oe
     * @param objectBrowser
     */
    public FrameBrowserEnvironment( ObjectEngine oe, ObjectBrowser objectBrowser )
    {
        super( oe );
        this.objectBrowser = objectBrowser;
    }

    @Override
    public void saveEnvironment()
    {
        save( objectBrowser.getMainFrame(), BROWSER_SESSION_FILE );
    }

    @Override
    public void restoreEnvironment()
    {
        restore( objectBrowser.getMainFrame(), BROWSER_SESSION_FILE );
    }

    @Override
    public void restoreEnvironment( JComponent component )
    {
        restore( component, BROWSER_SESSION_FILE );
    }

    @Override
    public Map<String, BrowserTask> refreshBrowserTaskList()
    {
        Map<String, BrowserTask> browserTaskList = new HashMap<>();
        for ( Task task : getOe().getTaskList() )
        {
            if ( ! task.isValid() )
                continue;

            browserTaskList.put( task.getTaskId(), new BrowserTask( task.getTaskId(), task.getApplication().getName() ) );
        }

        setCurrentTaskList( browserTaskList );
        return browserTaskList;
    }

    @Override
    public List<BrowserView> refreshBrowserViewList( BrowserTask browserTask )
    {
        if ( browserTask == null )
            return Collections.emptyList();

        List<BrowserView> list = new ArrayList<>();

        Task task = getOe().getTaskById( browserTask.taskId );
        if ( task == null )
        {
            JOptionPane.showMessageDialog( null, "This task has been reclaimed by the Garbage Collector.  Hit Refresh to remove.",
                                           "Invalid Task", JOptionPane.INFORMATION_MESSAGE );
            return list;
        }

        for ( View view : task.getViewList() )
        {
            if ( view.isInternal() )
                continue;

            Collection<String> nameList = view.getNameList();
            if ( nameList.size() == 0 )
            {
                if ( ! isShowUnnamedViews() )
                    continue;

                list.add(  new BrowserView( browserTask, view, UNNAMED_VIEW ) );
            }
            else
            {
                for ( String name : nameList )
                    list.add(  new BrowserView( browserTask, view, name ) );
            }
        }

        setCurrentViewList( list );
        return list;
    }

    @Override
    public View getView( BrowserView v )
    {
        Task task = getOe().getTaskById( v.task.taskId );
        View view = task.getViewByKey( v.viewId );
        if ( view == null )
            JOptionPane.showMessageDialog( null, "This view has been reclaimed by the Garbage Collector.  Hit Refresh to remove.",
                                           "Invalid View", JOptionPane.INFORMATION_MESSAGE );
        else
            view.setLazyLoad( false ); // Set lazy load so the browser doesn't change anything.

        return view;
    }

    @Override
    public void dropViewName( BrowserView view )
    {
        View v = getView( view );
        v.dropNameForView( view.viewName );
    }
}
