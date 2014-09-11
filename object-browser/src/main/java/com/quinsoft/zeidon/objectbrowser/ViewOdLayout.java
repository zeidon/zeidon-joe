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

package com.quinsoft.zeidon.objectbrowser;

import java.util.HashMap;
import java.util.Map;

import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.objectdefinition.LodDef;

/**
 * @author DG
 *
 */
class LodDefLayout
{
    private final BrowserEnvironment env;
    private final LodDef             lodDef;
    private final int                height;
    private final int                width;
    private final Map<EntityDef, EntityDefLayout> entityLayouts;
    
    /**
     * @param env
     * @param lodDef
     */
    LodDefLayout(BrowserEnvironment env, LodDef lodDef)
    {
        this.env = env;
        this.lodDef = lodDef;
        entityLayouts = new HashMap<EntityDef, EntityDefLayout>();
        
        EntityDef root = lodDef.getRoot();
        
        // Following will add the entity layout to the map.
        EntityDefLayout rootLayout = new EntityDefLayout( this, root );
        
        width = rootLayout.getWidth();
        height = lodDef.getHeight();
    }

    void addEntityDefLayout( EntityDef entityDef, EntityDefLayout entityDefLayout )
    {
        entityLayouts.put( entityDef, entityDefLayout );
    }

    EntityDefLayout getEntityDefLayout( EntityDef entityDef )
    {
        return entityLayouts.get( entityDef );
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
