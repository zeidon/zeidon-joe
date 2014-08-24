package com.quinsoft.zeidon.config;

import java.util.List;

/**
 * @author dgc
 *
 */
public interface JmxZeidonPreferencesMBean
{
    public void logPreferences();
    public void reloadPreferences();
    public String getDescription();
    public List<String> getAllProperties();
}
