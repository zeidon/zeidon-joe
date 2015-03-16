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
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.quinsoft.zeidon.EntityCursor;

/**
 * @author dgc
 *
 */
public class ZeidonListView extends ListView implements ZeidonDisplayView
{
    private final ZeidonAndroidViewDelegate viewDelegate = new ZeidonAndroidViewDelegate();
    private String listLayoutName;
    private int listLayoutId;
    private OnItemClickListener clickListener;
    private OnItemLongClickListener longClickListener;

    public ZeidonListView( Context context, AttributeSet attrs )
    {
        super( context, attrs );
        initialize( context, attrs );
    }

    public ZeidonListView( Context context, AttributeSet attrs, int defStyle )
    {
        super( context, attrs, defStyle );
        initialize( context, attrs );
    }
    
    private void initialize( Context context, AttributeSet attrs )
    {
        viewDelegate.loadConfig( this, attrs );
        listLayoutName = attrs.getAttributeValue( ZeidonAndroidViewDelegate.ZEIDON_NS, "list_layout_name" );
        listLayoutId = context.getResources().getIdentifier( listLayoutName, "layout", context.getPackageName() );

        super.setOnItemClickListener(new OnItemClickListener() {
            /**
             * Called when a new item was selected (in the Spinner)
             */
            @Override
            public void onItemClick( AdapterView<?> parent, android.view.View view, int pos, long id )
            {
                EntityCursor cursor = viewDelegate.getEntityCursor( );
                cursor.setPosition( pos );
                
                if ( clickListener != null )
                    clickListener.onItemClick( parent, view, pos, id );
            }
        });
        
        super.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick( AdapterView<?> parent, android.view.View view, int pos, long id )
            {
                EntityCursor cursor = viewDelegate.getEntityCursor( );
                cursor.setPosition( pos );
                
                if ( longClickListener != null )
                    return longClickListener.onItemLongClick( parent, view, pos, id );

                return false;
            }
        });

    }

    @Override
    public void setOnItemClickListener( OnItemClickListener listener )
    {
        clickListener = listener;
    }

    @Override
    public void setOnItemLongClickListener( OnItemLongClickListener listener )
    {
        longClickListener = listener;
    }

    @Override
    public void setZeidonParent( View parent )
    {
        viewDelegate.setParentView( parent );
    }

    @Override
    public void setFromOi( )
    {
        setAdapter( new EntityListAdapter() );
    }

    @Override
    public String getViewName()
    {
        return viewDelegate.getViewName( );
    }

    @Override
    public String getEntityName()
    {
        return viewDelegate.getEntityName();
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

    private class EntityListAdapter implements ListAdapter
    {
        private com.quinsoft.zeidon.View mappingView;

        private EntityListAdapter()
        {
            mappingView = viewDelegate.findView().newView();
            viewDelegate.setMappingView( mappingView );
        }
        
        @Override
        public int getCount()
        {
            if ( getAdapter() == null )
                return 0;
            
            return viewDelegate.getMappingEntityCursor().getEntityCount();
        }

        @Override
        public Object getItem( int position )
        {
            viewDelegate.getMappingEntityCursor().setPosition( position );
            return viewDelegate.getMappingEntityCursor().getEntityInstance();
        }

        @Override
        public long getItemId( int position )
        {
            return position;
        }

        @Override
        public int getItemViewType( int position )
        {
            return listLayoutId;
        }

        @Override
        public View getView( int position, View convertView, ViewGroup parent )
        {
            EntityCursor cursor = viewDelegate.getMappingEntityCursor( );
            cursor.setPosition( position );

            View listRow = convertView;
            if ( listRow == null )
            {
                LayoutInflater inflater = LayoutInflater.from( getContext() );
                listRow = inflater.inflate( listLayoutId, parent, false );
                
                // We've inflated a row for the ListView but listRow doesn't have a parent.
                // This will cause problems when we call setFromOi because the Zeidon views
                // in the listRow won't be able to find the Task/View information.  Set
                // the Zeidon parent for the top-level Zeidon android views under listRow.
                viewDelegate.setParentViewForChildViews( ZeidonListView.this, listRow );
            }

            if ( listRow instanceof ViewGroup )
                viewDelegate.setChildrenFromOi( (ViewGroup) listRow );
            else
                ((ZeidonDisplayView) listRow).setFromOi();

            return listRow;
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
            return getCount() == 0;
        }

        @Override
        public void registerDataSetObserver( DataSetObserver observer )
        {
        }

        @Override
        public void unregisterDataSetObserver( DataSetObserver observer )
        {
        }

        @Override
        public boolean areAllItemsEnabled()
        {
            return true;
        }

        @Override
        public boolean isEnabled( int position )
        {
            return true;
        }
    }
}
