/**
 * 
 */
package com.quinsoft.zeidon.android;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import com.quinsoft.zeidon.ZeidonException;

/**
 * EditText view for editing Zeidon attribute values.
 * 
 * @author dgc
 *
 */
public class ZeidonEditText extends EditText implements ZeidonDisplayView, ZeidonInputView
{
    private final ZeidonAndroidViewDelegate viewDelegate = new ZeidonAndroidViewDelegate();

    /**
     * @param context
     */
    public ZeidonEditText(Context context)
    {
        super( context );
        throw new ZeidonException("ZeidonEditText requires some attributes.");
    }

    /**
     * @param context
     * @param attrs
     */
    public ZeidonEditText(Context context, AttributeSet attrs)
    {
        super( context, attrs );
        viewDelegate.loadConfig( this, attrs );
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public ZeidonEditText(Context context, AttributeSet attrs, int defStyle)
    {
        super( context, attrs, defStyle );
        viewDelegate.loadConfig( this, attrs );
    }

    @Override
    public void copyValuesToOi()
    {
        String value = getText().toString();
        viewDelegate.setAttribute( value );
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
