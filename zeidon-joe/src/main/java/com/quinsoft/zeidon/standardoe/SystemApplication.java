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

    Copyright 2009-2014 QuinSoft
 */
package com.quinsoft.zeidon.standardoe;

import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.ZeidonException;

/**
 * Hard-coded values for Zeidon System application info.  Since this is automatically
 * loaded by the JOE we don't need to read it from the zeidon.app
 *
 */
class SystemApplication extends ApplicationImpl
{

    SystemApplication( String zeidonRootDir )
    {
        super( zeidonRootDir );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.Application#getName()
     */
    @Override
    public String getName()
    {
        return ObjectEngine.ZEIDON_SYSTEM_APP_NAME;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.Application#getObjectDir()
     */
    @Override
    public String getObjectDir()
    {
        return ObjectEngine.ZEIDON_SYSTEM_APP_NAME;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.Application#getPackage()
     */
    @Override
    public String getPackage()
    {
        throw new ZeidonException( "ZeidonSystem application has no package." );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.Application#nullStringEqualsEmptyString()
     */
    @Override
    public boolean nullStringEqualsEmptyString()
    {
        return false;
    }

    @Override
    public boolean isSystemApp()
    {
        return true;
    }
}
