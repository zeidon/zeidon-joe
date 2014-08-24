package com.quinsoft.zeidon.objectdefinition;

import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.ZeidonException;

/**
 * Specifies options when creating a dynamic view attribute.
 *
 * Dynamic view attributes are work attributes that are not defined in the XOD but
 * are created at runtime.
 *
 * @author dgc
 *
 */
public class DynamicViewAttributeConfiguration
{
    private String attributeName;
    private String domainName = "Text";

    /**
     * If true, if a dynamic attribute with the same already exists then don't
     * throw an exception.
     */
    private boolean canExist = false;

    public DynamicViewAttributeConfiguration()
    {
    }

    public boolean canExist()
    {
        return canExist;
    }

    public DynamicViewAttributeConfiguration setCanExist( boolean canExist )
    {
        this.canExist = canExist;
        return this;
    }

    /**
     * @return the attributeName
     */
    public String getAttributeName()
    {
        return attributeName;
    }

    /**
     * @param attributeName the attributeName to set
     * @return
     */
    public DynamicViewAttributeConfiguration setAttributeName( String attributeName )
    {
        if ( StringUtils.isBlank( attributeName ) )
            throw new ZeidonException( "attributeName is required" );

        this.attributeName = attributeName;
        return this;
    }

    /**
     * @return the domainName
     */
    public String getDomainName()
    {
        return domainName;
    }

    /**
     * @param domainName the domainName to set
     * @return
     */
    public DynamicViewAttributeConfiguration setDomainName( String domainName )
    {
        this.domainName = domainName;
        return this;
    }

}
