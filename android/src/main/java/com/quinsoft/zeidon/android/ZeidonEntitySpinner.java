/**
 * 
 */
package com.quinsoft.zeidon.android;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.View;

/**
 * Creates an Android Spinner that is populated by the twins of a Zeidon entity.
 * 
 * @author dgc
 *
 */
public class ZeidonEntitySpinner extends Spinner implements ZeidonDisplayView, ZeidonInputView
{
    private final ZeidonAndroidViewDelegate viewDelegate = new ZeidonAndroidViewDelegate();
    
    /**
     * When onItemSelected is called we update the position and then call the users onItemSelected
     * (if specified).  The user might attempt to update this control using ZeidonViewGroup.setFromOi()
     * and we need to prevent this control from creating a new adapter.
     */
    private boolean inSelect;

    /**
     * We will register an event listener so that we can set the attribute value
     * when the spinner is selected.  However, the app may want it's own listener
     * so we'll keep track of that here.
     */
    private OnItemSelectedListener selectedListener;

    public ZeidonEntitySpinner(Context context, AttributeSet attrs)
    {
        super( context, attrs );
        viewDelegate.loadConfig( this, attrs );
        initializeListener();
    }

    public ZeidonEntitySpinner(Context context, AttributeSet attrs, int defStyle)
    {
        super( context, attrs, defStyle );
        viewDelegate.loadConfig( this, attrs );
        initializeListener();
    }

    private void initializeListener()
    {
        super.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Called when a new item was selected (in the Spinner)
             */
            public void onItemSelected( AdapterView<?> parent, android.view.View view, int pos, long id )
            {
                EntityCursor cursor = viewDelegate.getEntityCursor( );
                if ( cursor.getPosition() == pos)
                    return;
                
                cursor.setPosition( pos );
                
                if ( selectedListener != null )
                {
                    inSelect = true; // Indicate we don't want a new adapter created if setFromOI() is called.
                    selectedListener.onItemSelected( parent, view, pos, id );
                    inSelect = false;
                }
            }

            public void onNothingSelected( AdapterView<?> parent )
            {
                if ( selectedListener != null )
                    selectedListener.onNothingSelected( parent );
            }

        });
    }

    @Override
    public void setOnItemSelectedListener( OnItemSelectedListener listener )
    {
        selectedListener = listener;
    }

    @Override
    public void copyValuesToOi()
    {
        // Nothing needs to be done here because it is handled by the onSelect listener.
        // This class implements the ZeidonInputView to indicate that it updates the
        // backing Zeidon view.
    }

    @Override
    public void setZeidonParent( android.view.View parent )
    {
        viewDelegate.setParentView( parent );
    }

    @Override
    public void setFromOi()
    {
        if ( inSelect )
            return;
        
        ArrayList<String> stringList = new ArrayList<String>();
        View view = viewDelegate.findMappingView( ).newView(); // Create a temp view.
        for ( EntityInstance ei : view.cursor( viewDelegate.getEntityName( ) ).eachEntity() )
        {
            String value = ei.getStringFromAttribute( viewDelegate.getAttributeName() );
            stringList.add( value );
        }

        EntityAdapter adapter = new EntityAdapter( stringList );
        setAdapter( adapter );
        setSelection( (int) viewDelegate.getEntityCursor( ).getPosition() );
    }

    @Override
    public String getViewName()
    {
        return viewDelegate.getViewName( );
    }

    @Override
    public String getEntityName()
    {
        return viewDelegate.getEntityName( );
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

    private class EntityAdapter implements SpinnerAdapter
    {
        private final List<String> stringList;
        
        private EntityAdapter( ArrayList<String> stringList )
        {
            this.stringList = stringList;
        }

        /**
         * Returns the Size of the ArrayList
         */
        @Override
        public int getCount()
        {
            return stringList.size();
        }

        /**
         * Returns one Element of the ArrayList at the specified position.
         */
        @Override
        public Object getItem( int position )
        {
            return stringList.get( position );
        }

        @Override
        public long getItemId( int position )
        {
            return position;
        }

        @Override
        public int getItemViewType( int position )
        {
            return android.R.layout.simple_spinner_dropdown_item;
        }

        /**
         * Returns the View that is shown when a element was selected.
         */
        @Override
        public android.view.View getView( int position, android.view.View convertView, ViewGroup parent )
        {
            TextView v = new TextView( getContext() );
            v.setTextColor( Color.BLACK );
            v.setText( stringList.get( position ) );
            return v;
        }

        @Override
        public int getViewTypeCount()
        {
            return 1;
        }

        @Override
        public boolean hasStableIds()
        {
            return false;
        }

        @Override
        public boolean isEmpty()
        {
            return false;
        }

        @Override
        public void registerDataSetObserver( DataSetObserver observer )
        {
        }

        @Override
        public void unregisterDataSetObserver( DataSetObserver observer )
        {
        }

        /**
         * The Views which are shown in when the arrow is clicked (In this case,
         * I used the same as for the "getView"-method.
         */
        @Override
        public android.view.View getDropDownView( int position, android.view.View convertView, ViewGroup parent )
        {
            return this.getView( position, convertView, parent );
        }
    }
}
