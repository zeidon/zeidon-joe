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

package com.quinsoft.zeidon.standardoe;

import java.util.HashMap;
import java.util.Map;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.domains.DomainClassLoader;
import com.quinsoft.zeidon.domains.DomainContext;
import com.quinsoft.zeidon.domains.TableDomainContext;
import com.quinsoft.zeidon.domains.TableEntry;
import com.quinsoft.zeidon.utils.PortableFileReader;
import com.quinsoft.zeidon.utils.PortableFileReader.PortableFileAttributeHandler;
import com.quinsoft.zeidon.utils.PortableFileReader.PortableFileEntityHandler.NullEntityHandler;

/**
 * List of domains for an application.  This handles loading the domains.
 *
 * @author DG
 *
 */
class DomainList
{
    private static final String S_DOMAIN_FILE = "zeidon.xdm";

    private final Map<String, Domain> domainMap = new HashMap<String,Domain>();
    private final Application application;

    public DomainList(Application application)
    {
        super();
        this.application = application;
    }

    Domain getDomain( String name )
    {
    	Domain domain = domainMap.get(name);
    	if ( domain == null )
    		throw new ZeidonException( "Domain '%s' does not exist for application %s",
    								   name, application.toString() );

    	return domain;
    }

    /**
     * Load the domains for this application.
     *
     * @param task Used mostly for logging.
     */
    void loadDomains( Task task )
    {
        // Load the domains for the application.
        String appDir = application.getObjectDir();
        String domainFile = appDir + "/" + DomainList.S_DOMAIN_FILE;
        DomainReader reader = new DomainReader( task );
        PortableFileReader.ReadPortableFile( task, domainFile, task.log(), reader );
    }

    private class DomainReader extends NullEntityHandler implements PortableFileAttributeHandler
    {
        private final Task task;
        private Domain currentDomain;
        private DomainContext currentContext;
        private TableEntryReader currentTableEntry;
        private Map<String,Object> domainProperties;

        private DomainReader( Task task )
        {
            this.task = task;
        }

        @Override
        public PortableFileAttributeHandler createEntity(PortableFileReader reader, int level, long flags)
        {
            String entityName = reader.getAttributeName();
            if ( entityName.equals( "Domain" ) )
            {
                domainProperties = new HashMap<String, Object>();
                return this;
            }
            else
            if ( entityName.equals( "Context" ) )
            {
                currentContext = currentDomain.newContext(task);
                return currentContext;
            }
            else
            if ( entityName.equals( "TableEntry" ) )
            {
                currentTableEntry = new TableEntryReader();
                return currentTableEntry;
            }
            else
            if ( entityName.equals( "DomainGroup" ) )
            {
                // Ignore domain group.
                return new NullAttributeHandler();
            }

            throw new ZeidonException( "Unknown entity name '%' in zeidon.xdm ", entityName );
        }

        @Override
        public void endEntityAttributes(PortableFileReader reader, String entityName, int currentLevel)
        {
        	try
        	{
	            if ( entityName.equals( "Domain" ) )
	            {
	                if ( ! domainProperties.containsKey( "Name" ))
	                    throw new ZeidonException( "Domain does not have 'Name' specified" ).appendMessage( "Values = " + domainProperties.toString() );

	                if ( ! domainProperties.containsKey( "JavaClass" ) )
                        throw new ZeidonException( "Domain does not have 'JavaClass' specified" ).appendMessage( "Domain = %s", domainProperties.get( "Name" ) );

	                currentDomain = loadNewDomain( task );
                    domainMap.put( currentDomain.getName(), currentDomain );
	            }
	            else
	            if ( entityName.equals( "Context" ) )
	                currentDomain.addContext( task, currentContext );
	            else
	            if ( entityName.equals( "TableEntry" ) )
	            {
	                ((TableDomainContext) currentContext).addTableEntry( task, currentTableEntry );
	            }
	            else
	            if ( entityName.equals( "DomainGroup" ) )
	            {
	                // Ignore domain group.
	                return;
	            }
        	}
        	catch ( Exception e )
        	{
        		throw ZeidonException.wrapException( e ).prependMessage("Domain = %s", currentDomain );
        	}
        }

        private Domain loadNewDomain( Task task )
        {
            DomainClassLoader loader = task.getObjectEngine().getDomainClassLoader();
            return loader.loadDomain( application, domainProperties, task );
        }

        @Override
        public void setAttribute(PortableFileReader reader )
        {
            domainProperties.put( reader.getAttributeName(), reader.getAttributeValue() );
        }
    } // class DomainReader

    private class TableEntryReader extends NullEntityHandler implements PortableFileAttributeHandler, TableEntry
    {

        private String externalValue;
        private String internalValue;

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
        public void setAttribute(PortableFileReader reader)
        {
            String name = reader.getAttributeName();
            if ( name.equals( "ExternalValue" ) )
                externalValue = reader.getAttributeValue();
            else
            if ( name.equals( "InternalValue" ) )
                internalValue = reader.getAttributeValue();
        }

        @Override
        public int getIndex()
        {
            throw new UnsupportedOperationException( "getIndex() not supported for TableEntryReader");
        }
    }
}
