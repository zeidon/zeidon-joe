/**
 *
 */
package com.quinsoft.zeidon.config;

/**
 * Defines the interface for retrieving Zeidon preferences.
 *
 * @author dgc
 *
 */
public interface ZeidonPreferences
{
    public String get( String groupName, String key, String defaultValue );
    public void reload();

    /**
     * Returns a string representation of all the property values.  Intended to
     * be used by the JMX interface for viewing all the values.
     *
     * @return
     */
    public String getAllProperties();
}
