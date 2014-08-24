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

package com.quinsoft.zeidon.domains;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.InvalidAttributeValueException;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.objectdefinition.ViewAttribute;
import com.quinsoft.zeidon.utils.JoeUtils;

/**
 * @author DG
 *
 */
public class StringDomain extends AbstractDomain
{
    public static final String EMPTY_STRING = "";
    
    private final int maxLth;
    
    public StringDomain(Application application, Map<String, Object> domainProperties, Task task )
    {
        super( application, domainProperties, task );
        String sMaxLth = (String) domainProperties.get( "MaxStringLth" );
        if ( StringUtils.isBlank( sMaxLth ) )
            maxLth = 0;
        else
            maxLth = Integer.parseInt( sMaxLth );
    }

    /**
     * Checks to see if the application treats null strings as equal
     * to empty strings.  If so, this will convert null to "".
     * 
     * @param string
     * @return
     */
    public static String checkNullString( Application app, Object string )
    {
        if ( string == null )
            return app.nullStringEqualsEmptyString() ? EMPTY_STRING : null;
        
        if ( string.equals( EMPTY_STRING ) )
            return EMPTY_STRING;
        
        return string.toString();
    }

    /**
     * Checks to see if the application for this domain treats null strings as equal
     * to empty strings.  If so, this will convert "" to null.
     * 
     * @param string
     * @return
     */
    public String checkNullString( Object string )
    {
        return checkNullString( getApplication(), string );
    }
    
    @Override
    public Object convertExternalValue(Task task, ViewAttribute viewAttribute, String contextName, Object externalValue)
    {
        if ( externalValue instanceof View )
        {
            String str = JoeUtils.serializeView( (View) externalValue );
            return str;
        }
        
        return checkNullString( externalValue );
    }
    
    @Override
    public void validateInternalValue( Task task, ViewAttribute viewAttribute, Object internalValue ) throws InvalidAttributeValueException
    {
        // It better be a string.
        if ( ! ( internalValue instanceof String ) )
            throw new InvalidAttributeValueException( viewAttribute, internalValue, "Value must be a string" );
        
        String string = checkNullString( internalValue );
            
        if ( string != null )
        {
            // If the max length is specified for the attribute, use it instead of the domain.
            if ( viewAttribute.getLength() != null )
            {
                if ( string.length() > viewAttribute.getLength() )
                     throw new InvalidAttributeValueException( viewAttribute, internalValue,
                                                                "Max length of %d exceeded.  Length = %d",
                                                                viewAttribute.getLength(), string.length() );
            }
            else
            // Use the default domain lth.
            if ( string.length() > maxLth )
                throw new InvalidAttributeValueException( viewAttribute, internalValue,
                                                           "Max length of %d exceeded.  Domain length = %d",
                                                           maxLth, string.length() );
        }
    }

    @Override
    public String convertToString(Task task, ViewAttribute viewAttribute, Object internalValue, String contextName)
    {
        return checkNullString( internalValue );
    }
    
    @Override
    public Integer convertToInteger(Task task, ViewAttribute viewAttribute, Object internalValue, String contextName)
    {
        return Integer.parseInt( (String) internalValue );
    }

    @Override
    public Double convertToDouble(Task task, ViewAttribute viewAttribute, Object internalValue, String contextName)
    {
        return Double.parseDouble( (String) internalValue );
    }

    /**
     * StringDomain needs to override compare() because it potentially needs to convert empty strings ("") to
     * null before doing the comparison.
     */
    @Override
    public int compare(Task task, ViewAttribute viewAttribute, Object internalValue, Object externalValue)
    {
        Object value = convertExternalValue( task, viewAttribute, null, externalValue );
        String s1 = checkNullString( internalValue );
        String s2 = checkNullString( value );
        
        Integer rc = compareNull( task, viewAttribute, s1, s2);
        if ( rc != null )
            return rc;

        int c = s1.compareTo( s2 );
        if ( c < 0 )
            return -1;
        if ( c > 0 )
            return 1;
        return 0;
    }
    
    @Override
    public boolean isNull( Task task, ViewAttribute viewAttribute, Object value )
    {
        if ( value == null )  // Null values are always null (duh).
            return true;
        
        if ( value instanceof String &&
             viewAttribute.getViewEntity().getViewOd().getApplication().nullStringEqualsEmptyString() &&
             StringUtils.isBlank( (String) value ) )
        {
            return true;
        }
        
        return false;
    }
}