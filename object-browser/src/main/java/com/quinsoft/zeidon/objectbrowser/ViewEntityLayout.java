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

import com.quinsoft.zeidon.objectdefinition.ViewEntity;

/**
 * @author DG
 *
 */
class ViewEntityLayout
{
    private final ViewOdLayout viewOdLayout;
    private final ViewEntity   viewEntity;
    private final int width;
    
    /**
     * @param viewOdLayout
     * @param viewEntity
     */
    ViewEntityLayout(ViewOdLayout viewOdLayout, ViewEntity viewEntity)
    {
        this.viewOdLayout = viewOdLayout;
        this.viewEntity = viewEntity;
        
        if ( viewEntity.getChildCount() == 0 )
        {
            width = 1;
        }
        else
        {
            int w = 0;
            for ( ViewEntity child : viewEntity.getChildren() )
            {
                ViewEntityLayout layout = new ViewEntityLayout( viewOdLayout, child );
                w += layout.getWidth();
            }
            width = w;
        }
        
        this.viewOdLayout.addViewEntityLayout( this.viewEntity, this );
    }

    public int getWidth()
    {
        return width;
    }

    public ViewOdLayout getViewOdLayout()
    {
        return viewOdLayout;
    }

    public ViewEntity getViewEntity()
    {
        return viewEntity;
    }
}
