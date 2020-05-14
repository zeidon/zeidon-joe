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

package com.quinsoft.zeidon.domains;

import java.util.Comparator;

import java.time.ZonedDateTime;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.Blob;
import com.quinsoft.zeidon.InvalidAttributeValueException;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.utils.PortableFileReader;

/**
 * @author DG
 *
 */
public class BaseDomainContext implements DomainContext
{
    private String name;
    private boolean isDefault;
    private Domain domain;
    private String editString;
    

    public BaseDomainContext(Domain domain)
    {
        super();
        this.domain = domain;
    }

    protected void setName( String name )
    {
        this.name = name;
    }
    
    protected void setIsDefault( boolean isDefault )
    {
        this.isDefault = isDefault;
    }

    protected void setEditString( String editString )
    {
        this.editString = editString;
    }
    
    @Override
    public String getEditString()
    {
        return editString;
    }

    @Override
    public String getName()
    {
        return name;
    }

    public Domain getDomain()
    {
        return domain;
    }

    public Application getApplication()
    {
        return domain.getApplication();
    }

    @Override
    public boolean isDefault()
    {
        return isDefault;
    }

    @Override
    public void setAttribute(PortableFileReader reader)
    {
        String propertyName = reader.getAttributeName();
        switch ( propertyName )
        {
            case "Name":           setName( reader.getAttributeValue() ); break;
            case "IsDefault":      isDefault = reader.getAttributeValue().startsWith( "Y" ); break;
            case "JavaEditString": setEditString( reader.getAttributeValue() ); break;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public int compare(Task task, Object o1, Object o2)
    {
        Comparator<Object> c = (Comparator<Object>) o1;
        return c.compare( o1, o2 );
    }

    @Override
    public Object convertExternalValue(Task task, AttributeDef attributeDef, Object value)
            throws InvalidAttributeValueException
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Blob convertToBlob(Task task, AttributeDef attributeDef, Object internalValue)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Boolean convertToBoolean(Task task, AttributeDef attributeDef, Object internalValue)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public ZonedDateTime convertToDate(Task task, AttributeDef attributeDef, Object internalValue)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Double convertToDouble(Task task, AttributeDef attributeDef, Object internalValue)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Integer convertToInteger(Task task, AttributeDef attributeDef, Object internalValue)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public String convertToString(Task task, AttributeDef attributeDef, Object internalValue)
    {
    	return internalValue.toString();
    }

    @Override
    public void validateInternalValue(Task task, AttributeDef attributeDef, Object value)
            throws InvalidAttributeValueException
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString()
    {
        return domain.toString() + " Context: " + getName();
    }
}