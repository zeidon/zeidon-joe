/**
 * 
 */
package com.quinsoft.zeidon.utils;

import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.config.HomeDirectoryFromString;

/**
 * If jboss conf directory is specified in properties, then use it.  Otherwise return
 * the string declared in the constructor.
 * 
 * @author dg
 *
 */
public class JbossHomeDirectory extends HomeDirectoryFromString
{
    public JbossHomeDirectory(String homeDirectory)
    {
        super( homeDirectory );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.config.HomeDirectory#getHomeDirectory()
     */
    @Override
    public String getHomeDirectory()
    {
        // See if jboss conf directory is specified.
        String path = System.getProperty("jboss.server.config.url");
        if ( StringUtils.isBlank( path ) )
            return super.getHomeDirectory();
        
        if ( path.startsWith( "file:" ) )
            path = path.substring( 5 );
        
        return path;
    }
}
