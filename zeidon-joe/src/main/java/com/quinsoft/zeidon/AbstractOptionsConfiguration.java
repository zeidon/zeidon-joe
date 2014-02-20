/**
 *
 */
package com.quinsoft.zeidon;

import java.util.HashMap;
import java.util.Map;

/**
 * Common code for getting activate/commit configuration values from task config.
 *
 * @author dgc
 *
 */
public abstract class AbstractOptionsConfiguration
{
    public static final String OI_SERVER_URL = "oiServerUrl";

    private final Task                  task;
    private final Map<String,String>    overrideMap;

    public AbstractOptionsConfiguration( Task task )
    {
        this.task = task;
        overrideMap = new HashMap<String, String>();
    }

    public abstract Application getApplication();

    public String getOiServerUrl()
    {
        return getConfigValue( getApplication().getName(), OI_SERVER_URL );
    }

    public void setOiServerUrl( String url )
    {
        overrideConfigValue( OI_SERVER_URL, url );
    }

    public AbstractOptionsConfiguration overrideConfigValue( String key, String value )
    {
        overrideMap.put( key, value );
        return this;
    }

    public String getConfigValue( String group, String key )
    {
        if ( overrideMap.containsKey( key ) )
            return overrideMap.get( key );

        return task.readZeidonConfig( group, key );
    }

    public String getConfigValue( String group, String key, String defaultValue )
    {
        if ( overrideMap.containsKey( key ) )
            return overrideMap.get( key );

        return task.readZeidonConfig( group, key, defaultValue );
    }

    public Task getTask()
    {
        return task;
    }
}
