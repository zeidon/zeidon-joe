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

import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.domains.TableDomain;
import com.quinsoft.zeidon.domains.TableEntry;
import com.quinsoft.zeidon.objectdefinition.ViewAttribute;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.objectdefinition.ViewOd;

/**
 * Implements a spinner for table domain values.
 * 
 * @author dgc
 *
 */
public class ZeidonDomainSpinner extends Spinner implements ZeidonDisplayView, ZeidonInputView
{
    private final ZeidonAndroidViewDelegate viewDelegate = new ZeidonAndroidViewDelegate();
    private List<TableEntry> tableEntries;
    private TableDomain tableDomain;
    
    /**
     * We will register an event listener so that we can set the attribute value
     * when the spinner is selected.  However, the app may want it's own listener
     * so we'll keep track of that here.
     */
    private OnItemSelectedListener selectedListener;

    public ZeidonDomainSpinner(Context context, AttributeSet attrs)
    {
        super( context, attrs );
        viewDelegate.loadConfig( this, attrs );
        initializeListener();
    }

    public ZeidonDomainSpinner(Context context, AttributeSet attrs, int defStyle)
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
                TableEntry tableEntry = (TableEntry) parent.getItemAtPosition( pos );
                viewDelegate.setAttribute( tableEntry.getInternalValue() );
                
                if ( selectedListener != null )
                    selectedListener.onItemSelected( parent, view, pos, id );
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

    /**
     * We can't initialize the adapter until the task for the ZeidonViewGroup has been set, so we
     * need to do it from setFromOI().  Use tableDomain to insure we only do it once.
     */
    private void initalizeAdapter()
    {
        if ( tableDomain != null )
            return;
        
        View view = viewDelegate.findMappingView( );
        ViewOd viewOd = view.getViewOd();
        EntityDef entityDef = viewOd.getEntityDef( viewDelegate.getEntityName( ) );
        ViewAttribute viewAttribute = entityDef.getAttribute( viewDelegate.getAttributeName() );
        Domain domain = viewAttribute.getDomain();
        if ( ! ( domain instanceof TableDomain ) )
            throw new ZeidonException( "Domain for attribute is not a TableDomain" )
                            .prependViewAttribute( viewAttribute );
        
        tableDomain = (TableDomain) domain;
        String contextName = viewDelegate.getContextName();
        Task task = viewDelegate.findTaskQualification( ).getTask();
        tableEntries = tableDomain.getTableEntries( task, contextName );
        DomainAdapter adapter = new DomainAdapter( tableEntries );
        setAdapter( adapter );
    }

    @Override
    public void setZeidonParent( android.view.View parent )
    {
        viewDelegate.setParentView( parent );
    }
    
    @Override
    public void setFromOi()
    {
        initalizeAdapter();
        
        viewDelegate.setEntityInstance();
        String value = viewDelegate.getStringFromAttribute( );
        int count = 0;
        for ( TableEntry entry : tableEntries )
        {
            if ( entry.getExternalValue().equals( value ) )
            {
                setSelection( count );
                break;
            }
            
            count++;
        }
    }

    @Override
    public void copyValuesToOi()
    {
        // Nothing needs to be done here because it is handled by the onSelect listener.
        // This class implements the ZeidonInputView to indicate that it updates the
        // backing Zeidon view.
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
    public View getMappingView()
    {
        return viewDelegate.getMappingView();
    }

    @Override
    public android.view.View getZeidonParent()
    {
        return viewDelegate.getParentView();
    }

    private class DomainAdapter implements SpinnerAdapter
    {
        private final List<TableEntry> entryList;

        private DomainAdapter(List<TableEntry> data)
        {
            this.entryList = data;
        }

        /**
         * Returns the Size of the ArrayList
         */
        @Override
        public int getCount()
        {
            return entryList.size();
        }

        /**
         * Returns one Element of the ArrayList at the specified position.
         */
        @Override
        public Object getItem( int position )
        {
            return entryList.get( position );
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
            v.setText( entryList.get( position ).getExternalValue() );
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
