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

import java.util.List;

import com.quinsoft.zeidon.Task;


/**
 * Additional methods for Table Domains.
 * This is used with dynamic table domains (and can be used elsewhere).
 * 
 * @author DG
 *
 */
public interface TableDomain extends Domain
{
    List<TableEntry> getTableEntries( Task task, String contextName );
    
    final class ImmutableTableEntry implements TableEntry
    {
        private final int index;
        private final String internalValue;
        private final String externalValue;
        
        public ImmutableTableEntry(int index, String internalValue, String externalValue)
        {
            this.index = index;
            this.internalValue = internalValue;
            this.externalValue = externalValue;
        }

        public ImmutableTableEntry(int index, TableEntry tableEntry)
        {
            this( index, tableEntry.getInternalValue(), tableEntry.getExternalValue() );
        }

        @Override
        public String getInternalValue()
        {
            return internalValue;
        }

        @Override
        public String getExternalValue()
        {        	
            return externalValue;
        }

        @Override
        public int getIndex()
        {
            return index;
        }
        
        @Override
        public String toString()
        {
            return String.format( "%d: %s/%s", index, internalValue, externalValue );
        }
    }
}
