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

package com.quinsoft.zeidon.standardoe;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;

import difflib.Delta;
import difflib.Delta.TYPE;
import difflib.DiffUtils;
import difflib.Patch;

/**
 * Compares two OIs and determines if they are the same.
 * 
 * Some day this will create a "patch" type result that can be used to see what
 * is different between the OIs.
 * 
 * @author DG
 *
 */
class ObjectInstanceComparer
{
    private static final Map<TYPE, String> COLORS =
            Collections.unmodifiableMap( new HashMap<TYPE, String>() {
            private static final long serialVersionUID = 1L;
        {
            put( TYPE.CHANGE,   "yellow"  );
            put( TYPE.DELETE,   "red"  );
            put( TYPE.INSERT,   "lightgreen"  );
        }} );

    final private ObjectInstance oi1;
    final private ObjectInstance oi2;
    
    private List<Object> list1;
    private List<Object> list2;
    private Patch patch;
    
    /**
     * @param oi1
     * @param oi2
     */
    ObjectInstanceComparer( ObjectInstance oi1, ObjectInstance oi2 )
    {
        this.oi1 = oi1;
        this.oi2 = oi2;
        createDiff();
    }

    boolean areEqual()
    {
        return patch.getDeltas().size() == 0;
    }

    /**
     * Create an HTML file that somewhat compares two OIs side-by-side.
     * Shamelessly copied from Ryoji Kodakari at http://tsrtesttest.appspot.com/wiki/diff-util
     * 
     * @param task
     */
    void displayDiff( Task task )
    {
        String filename = "/tmp/diff.html";
        PrintWriter stream = null;
        try
        {
            stream = new PrintWriter( filename );

            StringBuilder tl = new StringBuilder();
            StringBuilder tr = new StringBuilder();
            List<Delta> deltas = patch.getDeltas();
            int last = 0;
            for ( Delta delta : deltas )
            {
                if ( last + 1 < delta.getOriginal().getPosition() )
                {
                    tl.append( "<pre>" );
                    tr.append( "<pre>" );
                    for ( int i = last + 1; i < delta.getOriginal().getPosition(); i++ )
                    {
                        tl.append( list1.get( i ) + "\n" );
                        tr.append( list1.get( i ) + "\n" );
                    }
                    tl.append( "</pre>\n" );
                    tr.append( "</pre>\n" );
                }
                TYPE type = delta.getType();
                List<?> or = delta.getOriginal().getLines();
                tl.append( "<pre style='background-color:" + COLORS.get( type ) // <---
                        + ";'>\n" + StringUtils.join( or, "\n" ) + "\n</pre>" );
                List<?> re = delta.getRevised().getLines();
                tr.append( "<pre style='background-color:" + COLORS.get( type )
                        + ";'>\n" + StringUtils.join( re, "\n" ) );
                if ( or.size() > re.size() )
                {
                    int diff = or.size() - re.size() - 1;
                    tr.append( StringUtils.repeat( '\n', diff ) );
                }
                tr.append( "\n</pre>" );
                last = delta.getOriginal().last();
            }
            
            if ( last + 1 < list1.size() )
            { // last is not delta
                tl.append( "<pre>\n" );
                tr.append( "<pre>\n" );
                for ( int i = last + 1; i < list1.size(); i++ )
                {
                    tl.append( list1.get( i ) + "\n" );
                    tr.append( list1.get( i ) + "\n" );
                }
                tl.append( "</pre>\n" );
                tr.append( "</pre>\n" );
            }

            stream.println( "<html><table><tr><td style='vertical-align:top;'>"
                    + tl.toString() + "</td><td style='vertical-align:top;'>"
                    + tr.toString() + "</td></tr></table></html>" );
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e ).prependFilename( filename );
        }
        finally
        {
            if ( stream != null )
                stream.close();
        }
    }
    
    private String entityFlags( EntityInstanceImpl ei )
    {
        return String.format( " (CR:%s UP:%s IN:%s DE:%s EX:%s)",
                              ei.isCreated()  ? "Y" : "N",
                              ei.isUpdated()  ? "Y" : "N",
                              ei.isIncluded() ? "Y" : "N",
                              ei.isDeleted()  ? "Y" : "N",
                              ei.isExcluded() ? "Y" : "N" );
    }
    
    private List<Object> createList( ObjectInstance oi )
    {
        List<Object> list = new ArrayList<Object>();
        for ( EntityInstanceImpl ei : oi.getEntities( false ) )
        {
            list.add( ei.toString() + entityFlags( ei ) );
            for ( AttributeDef attributeDef : ei.getNonNullAttributeList() )
            {
                String s = String.format( "   %s: %s %s", attributeDef.getName(), 
                                                            ei.getStringFromAttribute( attributeDef ),
                                                            ei.isAttributeUpdated( attributeDef ) ? "(Up)" : "(Up)" );
                list.add( s );
            }
        }
        
        return list;
    }
    
    private void createDiff()
    {
        list1 = createList( oi1 );
        list2 = createList( oi2 );
        patch = DiffUtils.diff( list1, list2 );
    }
}
