/**
 *
 */
package com.quinsoft.zeidon.config;

import java.io.InputStream;
import java.util.prefs.Preferences;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.ini4j.zeidon.Config;
import org.ini4j.zeidon.Ini;
import org.ini4j.zeidon.IniPreferences;

import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.utils.JoeUtils;

/**
 * Reads preferences from zeidon.ini.
 *
 * @author dgc
 *
 */
public class ZeidonIniPreferences implements ZeidonPreferences
{
    private static final Logger LOG = Logger.getLogger( ZeidonIniPreferences.class );

    private       Preferences   preferences;
    private final String        iniFileName;

    public ZeidonIniPreferences( HomeDirectory homeDirectory, String jmxAppName )
    {
        iniFileName = FilenameUtils.concat( homeDirectory.getHomeDirectory(), "zeidon.ini" );
        loadZeidonIni();
        new JmxZeidonPreferences( this, "com.quinsoft.zeidon:type=ZeidonIniPreferences", jmxAppName, iniFileName );
    }

    /**
     * Explicitly set the file name to be loaded.
     * @param fileName
     * @param jmxAppName
     */
    public ZeidonIniPreferences( String fileName, String jmxAppName )
    {
        iniFileName = fileName;
        loadZeidonIni();
        new JmxZeidonPreferences( this, "com.quinsoft.zeidon:type=ZeidonIniPreferences", jmxAppName, iniFileName );
    }

    public ZeidonIniPreferences( InputStream iniFile )
    {
        iniFileName = "input stream";
        loadZeidonIni( iniFile );
    }

    @Override
    public String get( String groupName, String key, String defaultValue )
    {
        Preferences node = preferences.node( groupName );
        return node.get( key, defaultValue );
    }

    private void loadZeidonIni()
    {
        LOG.info( "Opening Preferences: " + iniFileName );
        InputStream iniFile = JoeUtils.getInputStream( null, iniFileName, getClass().getClassLoader() );
        try
        {
            loadZeidonIni( iniFile );
        }
        finally
        {
            IOUtils.closeQuietly( iniFile );
        }
    }

    private void loadZeidonIni( InputStream iniFile )
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
            preferences = new IniPreferences( ini );
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e ).prependFilename( iniFileName );
        }
    }

    @Override
    public void reload()
    {
        loadZeidonIni();
    }

    @Override
    public String getAllProperties()
    {
        try
        {
            StringBuilder builder = new StringBuilder();
            for ( String groupName : preferences.childrenNames() )
            {
                builder.append( "[" ).append( groupName ).append( "]\n" );
                Preferences group = preferences.node( groupName );
                for ( String key : group.keys() )
                    builder.append( key ).append( "=" ).append( group.get( key, null ) ).append( "\n" );
                builder.append( "\n" );
            }

            return builder.toString();
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e ).prependFilename( iniFileName );
        }
    }
}
