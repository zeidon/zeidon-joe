package com.quinsoft.zeidon.objectbrowser;

import com.quinsoft.zeidon.BrowserStarter;
import com.quinsoft.zeidon.ObjectEngine;

public class Starter implements BrowserStarter
{
    @Override
    public void startBrowser(ObjectEngine oe)
    {
        ObjectBrowser browser = new ObjectBrowser( oe );
        browser.startup();
    }
}
