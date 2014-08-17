/**
 *
 */
package com.quinsoft.zeidon.objectbrowser;

import com.quinsoft.zeidon.ObjectEngine;

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

    void saveEnvironment()
    {
        objectBrowser.saveEnvironment();
    }
}
