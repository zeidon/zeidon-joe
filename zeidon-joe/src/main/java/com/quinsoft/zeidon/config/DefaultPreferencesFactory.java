package com.quinsoft.zeidon.config;

import java.util.HashMap;
import java.util.Map;

import com.quinsoft.zeidon.Application;

/**
 * A simple implementation of the ZeidonPreferencesFactory that returns a supplied
 * object by default.
 *
 * @author dgc
 *
 */
public class DefaultPreferencesFactory implements ZeidonPreferencesFactory
{
    private ZeidonPreferences defaultPreferences;
    private final Map<String, ZeidonPreferences> applicationPreferences = new HashMap<String, ZeidonPreferences>();

    public DefaultPreferencesFactory( ZeidonPreferences defaultPreferences, String jmxAppName )
    {
        this.defaultPreferences = defaultPreferences;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.config.ZeidonPreferencesFactory#getPreferences(com.quinsoft.zeidon.Application)
     */
    @Override
    public ZeidonPreferences getPreferences( Application application )
    {
        if ( applicationPreferences.containsKey( application.getName() ) )
            return applicationPreferences.get( application.getName() );

        return defaultPreferences;
    }

    public void addApplicationPreferences( String appName, ZeidonPreferences preferences )
    {
        applicationPreferences.put( appName, preferences );
    }

    public void setDefaultPreferences( ZeidonPreferences defaultPreferences )
    {
        this.defaultPreferences = defaultPreferences;
    }

}
