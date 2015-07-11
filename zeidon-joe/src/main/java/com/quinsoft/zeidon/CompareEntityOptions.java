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
package com.quinsoft.zeidon;

/**
 * Defines the options that can be used when comparing entities.
 *
 */
public class CompareEntityOptions
{
    public final static CompareEntityOptions DEFAULT_OPTIONS = new CompareEntityOptions();

    private boolean commonAttributesOnly = false;
    private boolean compareDerivedAttributes = false;
    private boolean compareWorkAttributes = false;
    private boolean ignoreNullAttributes = false;

    public CompareEntityOptions()
    {
    }

    public boolean isCommonAttributesOnly()
    {
        return commonAttributesOnly;
    }

    public CompareEntityOptions setCommonAttributesOnly( boolean missingAttributesReturnFalse )
    {
        this.commonAttributesOnly = missingAttributesReturnFalse;
        return this;
    }

    public boolean isCompareDeriviedAttributes()
    {
        return compareDerivedAttributes;
    }

    public CompareEntityOptions setCompareDeriviedAttributes( boolean compareDeriviedAttributes )
    {
        this.compareDerivedAttributes = compareDeriviedAttributes;
        return this;
}

    public boolean isCompareWorkAttributes()
    {
        return compareWorkAttributes;
    }

    public CompareEntityOptions setCompareWorkAttributes( boolean compareWorkAttributes )
    {
        this.compareWorkAttributes = compareWorkAttributes;
        return this;
    }

    public boolean isIgnoreNullAttributes()
    {
        return ignoreNullAttributes;
    }

    public CompareEntityOptions setIgnoreNullAttributes( boolean ignoreNullAttributes )
    {
        this.ignoreNullAttributes = ignoreNullAttributes;
        return this;
    }

}
