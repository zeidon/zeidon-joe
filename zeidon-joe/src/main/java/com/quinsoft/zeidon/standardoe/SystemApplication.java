/**
 *
 */
package com.quinsoft.zeidon.standardoe;

import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.ZeidonException;

/**
 * Hard-coded values for Zeidon System application info.  Since this is automatically
 * loaded by the JOE we don't need to read it from the zeidon.app
 *
 * @author dgc
 *
 */
public class SystemApplication extends ApplicationImpl
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
     * @see com.quinsoft.zeidon.Application#getBinDir()
     */
    @Override
    public String getBinDir()
    {
        return ObjectEngine.ZEIDON_SYSTEM_APP_NAME;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.Application#getLocalDir()
     */
    @Override
    public String getLocalDir()
    {
        return ObjectEngine.ZEIDON_SYSTEM_APP_NAME;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.Application#getSharedDir()
     */
    @Override
    public String getSharedDir()
    {
        return ObjectEngine.ZEIDON_SYSTEM_APP_NAME;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.Application#getSourceDir()
     */
    @Override
    public String getSourceDir()
    {
        return ObjectEngine.ZEIDON_SYSTEM_APP_NAME;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.Application#getQlplrDir()
     */
    @Override
    public String getQlplrDir()
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
}
