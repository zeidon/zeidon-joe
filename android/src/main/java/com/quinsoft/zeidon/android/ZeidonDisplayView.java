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
/**
 * 
 */
package com.quinsoft.zeidon.android;

import com.quinsoft.zeidon.View;

/**
 * A Zeidon android views that displays data is an output view and must
 * implement this interface.
 * 
 * @author dgc
 *
 */
public interface ZeidonDisplayView
{
    /**
     * This is called from a parent ZeidonDisplayView to set data for the android view from
     * data in an OI.  If the view has child views then it is assumed that this call will
     * also set the values for them..
     */
    void setFromOi();
    
    /**
     * Gets the Zeidon view assigned to map this android View.  If this returns null then
     * the mapping view is assumed to be the same as the named view.
     * 
     * @return
     */
    View getMappingView();
    String getViewName();
    String getEntityName();
    
    void setZeidonParent( android.view.View parent );
    android.view.View getZeidonParent();
}
