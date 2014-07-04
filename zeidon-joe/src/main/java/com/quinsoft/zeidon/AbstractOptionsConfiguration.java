/**
 *
 */
package com.quinsoft.zeidon;

import java.util.HashMap;
import java.util.Map;

import com.quinsoft.zeidon.dbhandler.DbHandler;

/**
 * Common code for getting activate/commit configuration values from task config.
 *
 * @author dgc
 *
 */
public abstract class AbstractOptionsConfiguration
{
    public static final String OI_SOURCE_URL = "oiSourceUrl";

    private final Task                  task;
    private final Map<String,String>    overrideMap;

    /**
     * If not null then the activator/committer will use this DbHandler for processing.
     */
    private DbHandler dbHandler;

    public AbstractOptionsConfiguration( Task task )
    {
        this.task = task;
        overrideMap = new HashMap<String, String>();
    }

    public abstract Application getApplication();

    public String getOiSourceUrl()
    {
        return getConfigValue( getApplication().getName(), OI_SOURCE_URL );
    }

    public void setOiSourceUrl( String url )
    {
        overrideConfigValue( OI_SOURCE_URL, url );
    }

    /**
     * Returns a description of the source of the configuration.
     * E.g "/tmp/zeidon.ini"
     *
     * @return
     */
    public String getConfigSource()
    {
       return task.getObjectEngine().getZeidonPreferences( getApplication() ).getSourceDescription();
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

    public DbHandler getDbHandler()
    {
        return dbHandler;
    }

    public void setDbHandler( DbHandler dbHandler )
    {
        this.dbHandler = dbHandler;
    }
}
