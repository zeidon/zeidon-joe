package com.quinsoft.zeidon.jconsole;

import java.lang.management.ThreadInfo;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.SwingWorker;

/**
 * @author dgc
 *
 */
public class BrowserSwingWorker extends SwingWorker<List<Map.Entry<Long, ThreadInfo>>, Object>
{

    /**
     *
     */
    public BrowserSwingWorker()
    {
        // TODO Auto-generated constructor stub
    }

    @Override
    protected List<Entry<Long, ThreadInfo>> doInBackground() throws Exception
    {
        return Collections.emptyList();
    }

}
