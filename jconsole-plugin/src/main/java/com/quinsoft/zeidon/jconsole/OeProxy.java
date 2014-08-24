package com.quinsoft.zeidon.jconsole;

import com.quinsoft.zeidon.jmx.JmxObjectEngineMonitorMBean;

/**
 * @author dgc
 *
 */
public class OeProxy
{
    final String beanName;
    final JmxObjectEngineMonitorMBean proxy;

    public OeProxy( String beanName, JmxObjectEngineMonitorMBean proxy )
    {
        this.beanName = beanName;
        this.proxy = proxy;
    }
}
