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

    Copyright 2009-2012 QuinSoft
 */

package com.quinsoft.zeidon.objectbrowser;

import java.util.HashMap;
import java.util.Map;

import com.quinsoft.zeidon.objectdefinition.ViewEntity;
import com.quinsoft.zeidon.objectdefinition.ViewOd;

/**
 * @author DG
 *
 */
class ViewOdLayout
{
    private final BrowserEnvironment env;
    private final ViewOd             viewOd;
    private final int                height;
    private final int                width;
    private final Map<ViewEntity, ViewEntityLayout> entityLayouts;
    
    /**
     * @param env
     * @param viewOd
     */
    ViewOdLayout(BrowserEnvironment env, ViewOd viewOd)
    {
        this.env = env;
        this.viewOd = viewOd;
        entityLayouts = new HashMap<ViewEntity, ViewEntityLayout>();
        
        ViewEntity root = viewOd.getRoot();
        
        // Following will add the entity layout to the map.
        ViewEntityLayout rootLayout = new ViewEntityLayout( this, root );
        
        width = rootLayout.getWidth();
        height = viewOd.getHeight();
    }

    void addViewEntityLayout( ViewEntity viewEntity, ViewEntityLayout viewEntityLayout )
    {
        entityLayouts.put( viewEntity, viewEntityLayout );
    }

    ViewEntityLayout getViewEntityLayout( ViewEntity viewEntity )
    {
        return entityLayouts.get( viewEntity );
    }
    
    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }
}
