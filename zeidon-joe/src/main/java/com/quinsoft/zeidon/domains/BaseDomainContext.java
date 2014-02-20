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

    Copyright 2009-2012 QuinSoft
 */

package com.quinsoft.zeidon.domains;

import java.util.Comparator;

import org.joda.time.DateTime;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.Blob;
import com.quinsoft.zeidon.InvalidAttributeValueException;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.objectdefinition.ViewAttribute;
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
    
    public BaseDomainContext(Domain domain)
    {
        super();
        this.domain = domain;
    }

    protected void setName( String name )
    {
        this.name = name;
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
        if ( reader.getAttributeName().equals( "Name" ))
            setName( reader.getAttributeValue() );
        else
        if ( reader.getAttributeName().equals( "IsDefault" ))
            isDefault = reader.getAttributeValue().startsWith( "Y" );
    }

    @SuppressWarnings("unchecked")
    @Override
    public int compare(Task task, Object o1, Object o2)
    {
        Comparator<Object> c = (Comparator<Object>) o1;
        return c.compare( o1, o2 );
    }

    @Override
    public Object convertExternalValue(Task task, ViewAttribute viewAttribute, Object value)
            throws InvalidAttributeValueException
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Blob convertToBlob(Task task, ViewAttribute viewAttribute, Object internalValue)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public DateTime convertToDate(Task task, ViewAttribute viewAttribute, Object internalValue)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Double convertToDouble(Task task, ViewAttribute viewAttribute, Object internalValue)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Integer convertToInteger(Task task, ViewAttribute viewAttribute, Object internalValue)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public String convertToString(Task task, ViewAttribute viewAttribute, Object internalValue)
    { 
    	return internalValue.toString();
        //throw new UnsupportedOperationException();
    }

    @Override
    public void validateInternalValue(Task task, ViewAttribute viewAttribute, Object value)
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