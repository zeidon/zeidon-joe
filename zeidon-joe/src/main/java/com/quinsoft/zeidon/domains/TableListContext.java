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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.InvalidAttributeValueException;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.domains.TableDomain.ImmutableTableEntry;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.DomainType;
import com.quinsoft.zeidon.objectdefinition.InternalType;

/**
 * Implementation of TableDomainContext that keeps the list of table entries in an internal list.
 *
 * @author DG
 *
 */
public class TableListContext extends BaseDomainContext implements TableDomainContext
{
    /**
     * Map of the internal value to TableEntry.
     */
    private Map<String, TableEntry> internalMap = new HashMap<String, TableEntry>();
    private Map<String, TableEntry> externalMap = new HashMap<String, TableEntry>();
    private List<TableEntry>        entryList = new ArrayList<TableEntry>();

    /**
     * We use a string domain to convert external values to a string.
     */
    private StringDomain            stringConverter;

    private static final Map<String, Object> STRING_CONVERTER_PROPERTIES =
        Collections.unmodifiableMap( new HashMap<String, Object>() {
        private static final long serialVersionUID = 1L;
    {
        put( "Name", "StringConverter"  );
        put( "DomainType", DomainType.FORMAT.toString() );
        put( "DataType", InternalType.STRING.toString() );
    }} );

    public TableListContext(Domain domain, Task task)
    {
        super( domain );
        stringConverter = new StringDomain( getApplication(), STRING_CONVERTER_PROPERTIES, task );
    }

    @Override
    public List<TableEntry> getTableEntries(Task task)
    {
        return entryList;
    }

    @Override
    public String convertToString(Task task, AttributeDef attributeDef, Object internalValue)
    {
        String string = (String) stringConverter.convertExternalValue( task, null, attributeDef, null, internalValue );
        TableEntry v = internalMap.get( string );
        if ( v != null && v.getInternalValue() != null )
            return v.getExternalValue();

        // If attribute isn't required we can return 'string'.  It will be either null or "".
        // Change to permit return of empty string regardless of "required" flag ... dks/dgc 2011.04.27
     // if ( ! attributeDef.isRequired() && StringUtils.isBlank( string ) )
        if ( StringUtils.isBlank( string ) )
            return string;

        throw new ZeidonException( "Internal Error: Table value '%s' is invalid for %s", internalValue, toString() );
    }

    @Override
    public void validateInternalValue( Task task, AttributeDef attributeDef, Object value ) throws InvalidAttributeValueException
    {
        String string = (String) stringConverter.convertExternalValue( task, null, attributeDef, null, value );
        if ( externalMap.containsKey( string ) )
            return;

        if ( internalMap.containsKey( string ) )
            return;

        // If attribute isn't required we can return if string is either null or "".
        if ( ! attributeDef.isRequired() && StringUtils.isBlank( string ) )
            return;

        throw new InvalidAttributeValueException( attributeDef, value, "Invalid table domain value." );
    }

    @Override
    public Object convertExternalValue(Task task, AttributeDef attributeDef, Object value)
    {
        String string = (String) stringConverter.convertExternalValue( task, null, attributeDef, null, value );
        validateInternalValue( task, attributeDef, string );

        if ( externalMap.containsKey( string ) )
            return externalMap.get( string ).getInternalValue();

        TableEntry tableEntry = internalMap.get( string );

        // If tableEntry is null then we must be removing the value for a non-required attribute.
        if ( tableEntry == null )
            return null; //stringConverter.convertExternalValue( task, attributeDef, null, null );

        // If we get here then the input value matched an internal value so just return it.
        return tableEntry.getInternalValue();
    }

    @Override
    public void addTableEntry(Task task, String internalValue, String externalValue )
    {
        internalValue = StringDomain.checkNullString( getApplication(), internalValue );
        externalValue = StringDomain.checkNullString( getApplication(), externalValue );

        ImmutableTableEntry tableEntry = new ImmutableTableEntry( entryList.size(), internalValue, externalValue );
        internalMap.put( tableEntry.getInternalValue(), tableEntry );
        externalMap.put( tableEntry.getExternalValue(), tableEntry );
        entryList.add( tableEntry );
    }

    @Override
    public void addTableEntry(Task task, TableEntry tableEntry)
    {
        addTableEntry( task, tableEntry.getInternalValue(), tableEntry.getExternalValue() );
    }

    @Override
    public void resetTableEntries(Task task)
    {
        entryList = new ArrayList<TableEntry>();
    }

    @Override
    public TableEntry getTableEntryByInternalValue(Task task, String internalValue)
    {
        return internalMap.get( internalValue );
    }

    @Override
    public int compare(Task task, Object o1, Object o2)
    {
        TableEntry t1 = getTableEntryByInternalValue( task, (String) o1 );
        TableEntry t2 = getTableEntryByInternalValue( task, (String) o2 );
        return Integer.valueOf( t1.getIndex() ).compareTo( t2.getIndex() );
    }

    @Override
    public String toString()
    {
        return entryList.toString();
    }
}