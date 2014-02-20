package com.quinsoft.zeidon.config;

import java.io.File;

import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.utils.JoeUtils;

/**
 * Retrieves the home directory from ZEIDON_HOM env var.
 * 
 * @author DG
 * 
 */
public class HomeDirectoryFromEnvVar implements HomeDirectory
{
    private final String zeidonHomeDir;

    public HomeDirectoryFromEnvVar()
    {
        String rootDir = JoeUtils.getEnvProperty( "ZEIDON_HOME", true );
        
        // If the directory is a relative path then determine the absolute path.
        if ( rootDir.startsWith( "." ) )
        {
            rootDir = JoeUtils.parseFilename( rootDir );
            File file = new File( rootDir );
            if ( !file.isDirectory() )
                throw new ZeidonException( "Zeidon property value of '%s' is not a directory.", rootDir );
    
            rootDir = file.getAbsolutePath() + File.separator;
        }

        // If the path doesn't end with a directory separator, add it.
        if ( ! rootDir.endsWith( File.pathSeparator ) && ! rootDir.endsWith( "/" ) )
            rootDir = rootDir + "/";
        
        zeidonHomeDir = rootDir.intern();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.quinsoft.zeidon.HomeDirectory#getHomeDirectory()
     */
    @Override
    public String getHomeDirectory()
    {
        return zeidonHomeDir;
    }
}
