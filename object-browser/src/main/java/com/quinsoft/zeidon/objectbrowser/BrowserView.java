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
    final BrowserTask task;
    final long viewId;
    final long oiId;
    final String viewName;
    final String lodName;

    public BrowserView( BrowserTask task, long viewId, long oiId, String viewName, String lodName )
    {
        super();
        this.viewId = viewId;
        this.oiId = oiId;
        this.viewName = viewName;
        this.lodName = lodName;
        this.task = task;
    }

    public BrowserView( BrowserTask task, View view, String viewName )
    {
        this( task, view.getId(), view.getOiId(), viewName, view.getViewOd().getName() );
    }
}
