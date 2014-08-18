/**
 *
 */
package com.quinsoft.zeidon.objectbrowser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;

/**
 * @author dgc
 *
 */
public class FrameBrowserEnvironment extends BrowserEnvironment
{
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
        objectBrowser.saveEnvironment();
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

        Task task = getOe().getTaskById( browserTask.taskId );
        List<BrowserView> list = new ArrayList<>();

        for ( View view : task.getViewList() )
        {
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
        view.setLazyLoad( false ); // Set lazy load so the browser doesn't change anything.
        return view;
    }
}
