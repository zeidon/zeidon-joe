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
public class DynamicAttributeDefConfiguration
{
    private String attributeName;
    private String domainName = "Text";

    /**
     * If true, if a dynamic attribute with the same already exists then don't
     * throw an exception.
     */
    private boolean canExist = false;

    public DynamicAttributeDefConfiguration()
    {
    }

    public boolean canExist()
    {
        return canExist;
    }

    public DynamicAttributeDefConfiguration setCanExist( boolean canExist )
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
    public DynamicAttributeDefConfiguration setAttributeName( String attributeName )
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
    public DynamicAttributeDefConfiguration setDomainName( String domainName )
    {
        this.domainName = domainName;
        return this;
    }

}
