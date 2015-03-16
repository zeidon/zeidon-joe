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
