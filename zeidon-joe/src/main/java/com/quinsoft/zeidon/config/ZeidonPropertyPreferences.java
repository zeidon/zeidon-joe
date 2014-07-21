/**
 *
 */
package com.quinsoft.zeidon.config;

import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.text.StrLookup;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.apache.log4j.Logger;

import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.utils.JoeUtils;
import com.quinsoft.zeidon.utils.ZeidonInputStream;

/**
 * Reads Zeidon preferences from a .properties file.  Since properties aren't broken out
 * into multiple sections, the group name and key are concatenated with a period to create
 * a single property key.  For example, the call:
 *
 *      get( "MyGroup", "MyKey", "default" )
 *
 * will look for a property named "MyGroup.MyKey".
 *
 * @author dgc
 *
 */
public class ZeidonPropertyPreferences implements ZeidonPreferences
{
    private static final Logger LOG = Logger.getLogger( ZeidonPropertyPreferences.class );

    private final String     filename;
    private       Properties properties;
    private       String sourceDescription;
    
    /**
     * Used to translate environment variables in property values.
     */
    private final StrSubstitutor substitutor = new StrSubstitutor( StrLookup.systemPropertiesLookup());

    public ZeidonPropertyPreferences( String filename, String jmxAppName )
    {
        this.filename = substitutor.replace( filename );
        reload();
        new JmxZeidonPreferences( this, "com.quinsoft.zeidon:type=ZeidonPropertyPreferences", jmxAppName, filename );
    }

    @Override
    public String get( String groupName, String key, String defaultValue )
    {
        String fullKey = groupName + "." + key;
        String str = properties.getProperty( fullKey, defaultValue );
        str = substitutor.replace( str );
        return str;
    }

    @Override
    public void reload()
    {
        ZeidonInputStream stream = null;
        try
        {
            properties = new Properties();
            stream = JoeUtils.getInputStream( null, filename, this.getClass().getClassLoader() );
            if ( stream == null )
                throw new ZeidonException( "Couldn't find property file" );
            
            LOG.info( "Opening properties from: " + stream.getDescription() );
            properties.load( stream );
            sourceDescription = stream.getDescription();
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e ).prependFilename( filename );
        }
        finally
        {
            IOUtils.closeQuietly( stream );
        }
    }

    @Override
    public String getAllProperties()
    {
        StringBuilder builder = new StringBuilder();
        for ( Object key : properties.keySet() )
            builder.append( key ).append( "=" ).append( properties.getProperty( key.toString() ) ).append( "\n" );

        return builder.toString();
    }

    @Override
    public String getSourceDescription()
    {
        return sourceDescription;
    }
}
