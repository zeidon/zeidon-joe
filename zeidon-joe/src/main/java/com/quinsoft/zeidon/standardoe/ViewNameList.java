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
package com.quinsoft.zeidon.standardoe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;

/**
 * This keeps a mapping of view names and views.  Adding/removing names is thread-safe.
 * 
 * @author DG
 *
 */
class ViewNameList
{
    private Map<String, View> viewNameList = new HashMap<String, View>();

    synchronized void setNameForView( String viewName, View view )
    {
        viewNameList.put( viewName, view );
    }

    synchronized void dropNameForView( String viewName, View view )
    {
        View v = viewNameList.get( viewName );
        
        // If the name doesn't exist, just return.  We won't throw an error.
        if ( v == null )
            return;
        
        if ( v != view )
            throw new ZeidonException("Attempting to drop a view name '%s' that is tied to another view %s",
                                      viewName, v.toString() );
        
        viewNameList.remove( viewName );
    }

    /**
     * Returns all the names for this view.
     * 
     * @param view
     * @return
     */
    synchronized List<String> getAllViewNames( View view )
    {
        ArrayList<String> names = new ArrayList<String>();
        for ( String n : viewNameList.keySet())
        {
            if ( viewNameList.get( n ) == view )
                names.add( n );
        }
        
        Collections.sort( names );
        return names;
    }

    synchronized Collection<String> getAllViewNames()
    {
        return viewNameList.keySet();
    }

    synchronized Collection<View> getAllNamedViews()
    {
        return new HashSet<View>( viewNameList.values() );
    }
    
    synchronized View getViewByName(String name)
    {
        return viewNameList.get( name );
    }
    
    synchronized void dropView( View view )
    {
        Iterator<View> iter = viewNameList.values().iterator();
        while ( iter.hasNext() )
        {
            View v = iter.next();
            if ( v == view )
                iter.remove();
        }
    }

    /*
     * Following two methods were used for testing because we couldn't get the task to drop.
     * Don't need them now but never know...
    synchronized void dropAllViews( )
    {
        Iterator<View> iter = viewNameList.values().iterator();
        while ( iter.hasNext() )
        {
            View v = iter.next();
            //if ( v == view )
                iter.remove();
                v.drop();
        }
    }
    
    synchronized void loopThruViews( )
    {
    	String s = null;
        Iterator<View> iter = viewNameList.values().iterator();
        while ( iter.hasNext() )
        {
            View v = iter.next();
            s = v.getTask().toString();
        }
    }
    */

}
