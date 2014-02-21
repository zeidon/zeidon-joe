/**
 * 
 */
package com.quinsoft.zeidon.android;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

import com.quinsoft.zeidon.ZeidonException;

/**
 * @author dgc
 *
 */
public class ZeidonCheckBox extends CheckBox implements ZeidonDisplayView, ZeidonInputView, OnClickListener
{
    private final ZeidonAndroidViewDelegate viewDelegate = new ZeidonAndroidViewDelegate();
    private String onValue;
    private String offValue;

    /**
     * We will register an event listener so that we can set the attribute value
     * when the checkbox is clicked.  However, the app may want it's own listener
     * so we'll keep track of that here.
     */
    private OnClickListener clickListener;

    public ZeidonCheckBox(Context context, AttributeSet attrs)
    {
        super( context, attrs );
        init( attrs );
    }

    public ZeidonCheckBox(Context context, AttributeSet attrs, int defStyle)
    {
        super( context, attrs, defStyle );
        init( attrs );
    }
    
    private void init( AttributeSet attrs )
    {
        viewDelegate.loadConfig( this, attrs );
        onValue  = attrs.getAttributeValue( ZeidonAndroidViewDelegate.ZEIDON_NS, "on_value" );
        offValue = attrs.getAttributeValue( ZeidonAndroidViewDelegate.ZEIDON_NS, "off_value" );
        super.setOnClickListener( this );
    }

    @Override
    public void onClick( View v )
    {
        assert v == this;
        
        if ( isChecked() )
            viewDelegate.setAttribute( onValue );
        else
            viewDelegate.setAttribute( offValue );
        
        if ( clickListener != null )
            clickListener.onClick( v );
    }

    @Override
    public void setOnClickListener( OnClickListener l )
    {
        clickListener = l;
    }

    @Override
    public void copyValuesToOi()
    {
        // Nothing needs to be done here because it is handled by the onClick listener.
        // This class implements the ZeidonInputView to indicate that it updates the
        // backing Zeidon view.
    }

    @Override
    public void setZeidonParent( View parent )
    {
        viewDelegate.setParentView( parent );
    }

    @Override
    public void setFromOi()
    {
        viewDelegate.setEntityInstance();
        String value = viewDelegate.getStringFromAttribute( );
        if ( onValue.equals( value ) )
            setChecked( true );
        else
        if ( offValue.equals( value ) )
            setChecked( false );
        else
            throw new ZeidonException( "Unknown checkbox value form OI" )
                            .appendMessage( "Attribute: %s", viewDelegate.getAttributeName() )
                            .appendMessage( "Value: %s", value );
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
