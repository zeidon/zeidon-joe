/**
 *
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
