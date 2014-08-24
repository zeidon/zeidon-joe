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
/**
 * 
 */
package com.quinsoft.zeidon.android;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.quinsoft.zeidon.TaskQualification;

/**
 * @author dgc
 *
 */
public class ZeidonViewGroup extends LinearLayout implements ZeidonTaskQualifier, ZeidonDisplayView, ZeidonInputView
{
    private final ZeidonAndroidViewDelegate viewDelegate = new ZeidonAndroidViewDelegate();
    private TaskQualification taskQualification;
    
    /**
     * @param context
     */
    public ZeidonViewGroup(Context context)
    {
        super( context );
    }

    /**
     * @param context
     * @param attrs
     */
    public ZeidonViewGroup(Context context, AttributeSet attrs)
    {
        super( context, attrs );
        viewDelegate.loadConfig( this, attrs );
    }

    /* (non-Javadoc)
     * @see android.view.ViewGroup#onLayout(boolean, int, int, int, int)
     */
    @Override
    protected void onLayout( boolean changed, int l, int t, int r, int b )
    {
        super.onLayout( changed, l, t, r, b );
    }

    @Override
    public TaskQualification getTaskQualification()
    {
        if ( taskQualification != null )
            return taskQualification;
            
        // If this ZeidonViewGroup has a parent then we'll try that.
        View p = (View) getParent();
        if ( p != null )
            return viewDelegate.findTaskQualification( p );

        return viewDelegate.findTaskQualification();
    }

    public void setTaskQualification( TaskQualification taskQualification )
    {
        this.taskQualification = taskQualification;
    }

    @Override
    public void setZeidonParent( android.view.View parent )
    {
        viewDelegate.setParentView( parent );
    }

    /**
     * This sets the task qualification for this ZeidonViewGroup and initializes all child
     * android views that have Zeidon mappings.
     * 
     * @param taskQual
     */
    public void setFromOi( TaskQualification taskQual )
    {
        setTaskQualification( taskQual );
        setFromOi();
    }

    /**
     * Sets the values for child android views that have Zeidon mappings.  Assumes that the
     * task qualification has already been set for this view or one of its parents.
     */
    @Override
    public void setFromOi()
    {
        viewDelegate.setChildrenFromOi( this );
    }

    @Override
    public String getEntityName()
    {
        return viewDelegate.getEntityName( );
    }

    @Override
    public String getViewName()
    {
        return viewDelegate.getViewName( );
    }

    @Override
    public void copyValuesToOi()
    {
        viewDelegate.copyValuesToOi( this );
    }

    @Override
    public com.quinsoft.zeidon.View getMappingView()
    {
        return viewDelegate.getMappingView();
    }

    @Override
    public android.view.View getZeidonParent()
    {
        return viewDelegate.getParentView();
    }
}
