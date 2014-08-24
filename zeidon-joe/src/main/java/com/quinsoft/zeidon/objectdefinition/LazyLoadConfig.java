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

import java.util.EnumSet;


/**
 * @author DG
 *
 */
public class LazyLoadConfig
{
    public enum LazyLoadFlags
    {
        IS_LAZYLOAD,
        HAS_LAZYLOAD_PARENT,
        HAS_LAZYLOAD_CHILD
    }

    private final EnumSet<LazyLoadFlags> flags = EnumSet.noneOf( LazyLoadFlags.class );

    /**
     * If an entity has a lazy load parent, this is the parent.
     */
    private ViewEntity lazyLoadParent;

    public void setFlag( LazyLoadFlags flag )
    {
        flags.add( flag );
    }

    public boolean isLazyLoad()
    {
        return flags.contains( LazyLoadFlags.IS_LAZYLOAD );
    }

    /**
     * True if this entity has a child that is lazy loaded.
     * @return
     */
    public boolean hasLazyLoadChild()
    {
        return flags.contains( LazyLoadFlags.HAS_LAZYLOAD_CHILD );
    }

    /**
     * @return True if this entity has a parent that is flagged as lazy load.
     */
    public boolean hasLazyLoadParent()
    {
        return flags.contains( LazyLoadFlags.HAS_LAZYLOAD_PARENT );
    }

    /**
     * Get the parent that is flagged as lazy load.
     * @return
     */
    public ViewEntity getLazyLoadParent()
    {
        return lazyLoadParent;
    }

    public void setLazyLoadParent( ViewEntity lazyLoadParent )
    {
        this.lazyLoadParent = lazyLoadParent;
    }

    @Override
    public String toString()
    {
        return flags.toString();
    }
}
