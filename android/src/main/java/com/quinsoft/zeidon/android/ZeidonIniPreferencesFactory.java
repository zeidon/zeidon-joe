/**
 * 
 */
package com.quinsoft.zeidon.android;

import java.io.InputStream;
import java.util.prefs.Preferences;
import java.util.prefs.PreferencesFactory;

import org.ini4j.zeidon.Config;
import org.ini4j.zeidon.Ini;
import org.ini4j.zeidon.IniPreferences;

import com.quinsoft.zeidon.ZeidonException;

/**
 * @author dgc
 *
 */
public class ZeidonIniPreferencesFactory implements PreferencesFactory
{
    private IniPreferences config;

    /**
     * @param homeDirectory
     */
    public ZeidonIniPreferencesFactory( InputStream iniFile )
    {
        try
        {
            if ( iniFile == null )
                throw new ZeidonException( "Could not find zeidon.ini" );

            Config iniConfig = new Config();
            iniConfig.setLowerCaseOption( true );
            iniConfig.setLowerCaseSection( true );
            iniConfig.setEscape( false );
            Ini ini = new Ini();
            ini.setConfig( iniConfig );
            ini.load( iniFile );
            config = new IniPreferences( ini );
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e );
        }
    }

    /* (non-Javadoc)
     * @see java.util.prefs.PreferencesFactory#systemRoot()
     */
    @Override
    public Preferences systemRoot()
    {
        return config;
    }

    /* (non-Javadoc)
     * @see java.util.prefs.PreferencesFactory#userRoot()
     */
    @Override
    public Preferences userRoot()
    {
        return config;
    }

}
