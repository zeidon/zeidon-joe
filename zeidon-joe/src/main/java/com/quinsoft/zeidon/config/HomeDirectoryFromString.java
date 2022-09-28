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

/**
 * Implementation of HomeDirectory that is a supplied string.
 *
 * @author DG
 *
 */
public class HomeDirectoryFromString implements HomeDirectory
{
    private final String homeDirectory;

    /**
     * @param homeDirectory
     * @param zeidonLogger
     */
    public HomeDirectoryFromString(String homeDirectory, ZeidonLogger zeidonLogger)
    {
        this.homeDirectory = homeDirectory;
        zeidonLogger.info( "Home directory: %s", homeDirectory );
    }


    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.config.HomeDirectory#getHomeDirectory()
     */
    @Override
    public String getHomeDirectory()
    {
        return homeDirectory;
    }

}
