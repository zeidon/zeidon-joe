/**
    This file is part of the Zeidon Java Object Engine (Zeidon JOE).

    Zeidon JOE is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Zeidon JOE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with Zeidon JOE.  If not, see <http://www.gnu.org/licenses/>.

    Copyright 2009-2014 QuinSoft
 */
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
