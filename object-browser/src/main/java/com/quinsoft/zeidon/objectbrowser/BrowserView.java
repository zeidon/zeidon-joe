/**
 *
 */
package com.quinsoft.zeidon.objectbrowser;

import com.quinsoft.zeidon.View;

/**
 * @author dgc
 *
 */
public class BrowserView
{
    public final BrowserTask task;
    public final long viewId;
    public final long oiId;
    public final String viewName;
    public final String lodName;
    public final String appName;

    public BrowserView( BrowserTask task, long viewId, long oiId, String viewName, String lodName, String appName )
    {
        super();
        this.viewId = viewId;
        this.oiId = oiId;
        this.viewName = viewName;
        this.lodName = lodName;
        this.task = task;
        this.appName = appName;
    }

    public BrowserView( BrowserTask task, View view, String viewName )
    {
        this( task, view.getId(), view.getOiId(), viewName, view.getViewOd().getName(), view.getViewOd().getApplication().getName() );
    }
}
