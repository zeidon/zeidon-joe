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

    Copyright 2009-2015 QuinSoft
 */
package com.quinsoft.zeidon.config;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.quinsoft.zeidon.utils.JoeUtils;

/**
 * A wrapper class around a ZeidonPreferences implementation to make it easier to
 * manage the preferences via JMX.
 *
 * @author dgc
 *
 */
public class JmxZeidonPreferences implements JmxZeidonPreferencesMBean
{
    private static final Logger LOG = Logger.getLogger( JmxZeidonPreferences.class );

    private final ZeidonPreferences zeidonPreferences;
    private final String            description;

    public JmxZeidonPreferences( ZeidonPreferences zeidonPreferences,
                                 String            beanName,
                                 String            jmxAppName,
                                 String            description )
    {
        this.zeidonPreferences = zeidonPreferences;
        this.description = description;
        JoeUtils.RegisterJmxBean( this, beanName, jmxAppName );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.config.JmxZeidonPreferencesMBean#logPreferences()
     */
    @Override
    public void logPreferences()
    {
        LOG.info( zeidonPreferences.getAllProperties() );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.config.JmxZeidonPreferencesMBean#reloadPreferences()
     */
    @Override
    public void reloadPreferences()
    {
        zeidonPreferences.reload();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.config.JmxZeidonPreferencesMBean#getDescription()
     */
    @Override
    public String getDescription()
    {
        return description;
    }

    @Override
    public List<String> getAllProperties()
    {
        return Arrays.asList( zeidonPreferences.getAllProperties().split( "\n" ) );
    }

}
