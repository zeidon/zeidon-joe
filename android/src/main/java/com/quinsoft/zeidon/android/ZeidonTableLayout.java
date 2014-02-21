/**
 * 
 */
package com.quinsoft.zeidon.android;

import org.apache.commons.lang3.StringUtils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.quinsoft.zeidon.EntityInstance;

/**
 * @author dgc
 *
 */
public class ZeidonTableLayout extends TableLayout implements ZeidonDisplayView, ZeidonInputView
{
    private final ZeidonAndroidViewDelegate viewDelegate = new ZeidonAndroidViewDelegate();
    private final String  rowLayoutName;
    private final String  headerLayoutName;
    private final boolean hasHeaders;

    public ZeidonTableLayout(Context context, AttributeSet attrs)
    {
        super( context, attrs );
        viewDelegate.loadConfig( this, attrs );
        rowLayoutName = attrs.getAttributeValue( ZeidonAndroidViewDelegate.ZEIDON_NS, "row_layout_name" );
        headerLayoutName = attrs.getAttributeValue( ZeidonAndroidViewDelegate.ZEIDON_NS, "header_layout_name" );
        hasHeaders = ! StringUtils.isBlank( headerLayoutName );
    }

    @Override
    public void setZeidonParent( android.view.View parent )
    {
        viewDelegate.setParentView( parent );
    }

    @Override
    public void setFromOi()
    {
        // Remove any current items in the table.
        removeAllViews();
        
        Context context = getContext();
        
        // Header layout supplied?
        if ( hasHeaders )
        {
            int layoutId = context.getResources().getIdentifier( headerLayoutName, "layout", context.getPackageName() );
            LayoutInflater inflater = LayoutInflater.from( context );
            View tableRow = inflater.inflate( layoutId, null );
            this.addView(tableRow, new TableLayout.LayoutParams( LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT ) );
        }
        
        int layoutId = context.getResources().getIdentifier( rowLayoutName, "layout", context.getPackageName() );
        LayoutInflater inflater = LayoutInflater.from( context );
        
        com.quinsoft.zeidon.View view = viewDelegate.findMappingView( );
        for ( @SuppressWarnings("unused") EntityInstance ei : view.cursor( getEntityName() ).eachEntity() )
        {
            View tableRow = inflater.inflate( layoutId, null );
            this.addView(tableRow, new TableLayout.LayoutParams( LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT ) );
            viewDelegate.setChildrenFromOi( (TableRow) tableRow );
        }
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

    @Override
    public void copyValuesToOi()
    {
        com.quinsoft.zeidon.View view = viewDelegate.findMappingView( );
        String entityName = getEntityName();
        view.cursor( entityName ).setFirst();
        
        // If this table has a header row we need to skip it.
        int startIdx = 0;
        if ( hasHeaders )
            startIdx = 1;
        
        for ( int i = startIdx; i < getChildCount(); i++ )
        {
            TableRow tableRow = (TableRow) getChildAt( i );
            viewDelegate.copyValuesToOi( tableRow );
            view.cursor( entityName ).setNextContinue();
        }
    }
}
