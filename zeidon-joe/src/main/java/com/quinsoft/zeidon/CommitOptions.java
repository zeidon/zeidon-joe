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
package com.quinsoft.zeidon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;

/**
 * Optional options for commit.
 *
 * @author dgc
 *
 */
public class CommitOptions extends AbstractOptionsConfiguration
{
    private EnumSet<CommitFlags> control = CommitFlags.NONE;
    private List<View>           viewList;

    public CommitOptions( TaskQualification task )
    {
        super( task.getTask() );
    }

    @Override
    public Application getApplication()
    {
        return viewList.get( 0 ).getViewOd().getApplication();
    }

    public EnumSet<CommitFlags> getControl()
    {
        return control;
    }

    public CommitOptions setControl( EnumSet<CommitFlags> control )
    {
        this.control = control;
        return this;
    }

    @Override
    public CommitOptions overrideConfigValue( String key, String value )
    {
        super.overrideConfigValue( key, value );
        return this;
    }

    public Collection<View> getViewList()
    {
        return viewList;
    }

    public CommitOptions setViewList( List<View> viewList )
    {
        this.viewList = viewList;
        return this;
    }

    public CommitOptions addView( View view )
    {
        if ( viewList == null )
            viewList = new ArrayList<View>();

        viewList.add( view );
        return this;
    }
}
