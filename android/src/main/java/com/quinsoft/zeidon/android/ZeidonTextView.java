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

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.quinsoft.zeidon.ZeidonException;

/**
 * @author dgc
 *
 */
public class ZeidonTextView extends TextView implements ZeidonDisplayView
{
    private final ZeidonAndroidViewDelegate viewDelegate = new ZeidonAndroidViewDelegate();

    /**
     * @param context
     */
    public ZeidonTextView(Context context)
    {
        super( context );
        throw new ZeidonException("ZeidonTextView requires some attributes.");
    }

    /**
     * @param context
     * @param attrs
     */
    public ZeidonTextView(Context context, AttributeSet attrs)
    {
        super( context, attrs );
        viewDelegate.loadConfig( this, attrs );
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public ZeidonTextView(Context context, AttributeSet attrs, int defStyle)
    {
        super( context, attrs, defStyle );
        viewDelegate.loadConfig( this, attrs );
    }

    @Override
    public void setZeidonParent( android.view.View parent )
    {
        viewDelegate.setParentView( parent );
    }

    @Override
    public void setFromOi()
    {
        viewDelegate.setEntityInstance();
        String value = viewDelegate.getStringFromAttribute( );
        setText( value );
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
