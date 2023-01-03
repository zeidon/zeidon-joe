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

import com.quinsoft.zeidon.ZeidonLogger;
import com.quinsoft.zeidon.utils.JoeUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.nio.file.FileSystems;

/**
 * Retrieves the home directory from ZEIDON_HOM env var.
 *
 * @author DG
 *
 */
public class HomeDirectoryFromEnvVar implements HomeDirectory
{
    private final String zeidonHomeDir;

    public HomeDirectoryFromEnvVar(ZeidonLogger zeidonLogger)
    {
        String rootDir = JoeUtils.getEnvProperty( "ZEIDON_HOME", true );
        if ( StringUtils.isNotBlank( rootDir ) )
            rootDir = FileSystems.getDefault().getPath( rootDir ).normalize().toAbsolutePath().toString();

        // If the path doesn't end with a directory separator, add it.
        if ( ! rootDir.endsWith( File.pathSeparator ) && ! rootDir.endsWith( "/" ) )
            rootDir = rootDir + "/";

        zeidonLogger.info( "Home directory: %s", rootDir );
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
