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
package com.quinsoft.zeidon.standardoe;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.CacheMap;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.UnknownLodDefException;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.objectdefinition.LodDef;
import com.quinsoft.zeidon.utils.CacheMapImpl;
import com.quinsoft.zeidon.utils.PortableFileReader;
import com.quinsoft.zeidon.utils.PortableFileReader.PortableFileAttributeHandler;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author DG
 *
 */
class ApplicationImpl implements Application, PortableFileAttributeHandler
{
    /**
     * List of attribute values loaded from zeidon.app.  They are stored in a hash
     * map to make it easier to load them.
     */
    private final Map<String, String> attributes = new LinkedHashMap<String,String>();
    private final Map<String, LodDef> lodDefList = new HashMap<String, LodDef>();
    private final String              zeidonRootDir;
    private final ViewNameList        viewNameList = new ViewNameList();
    private final ObjectEngine        objectEngine;
    private       DomainList          domainList;
    private       CacheMap cacheMap;

    /**
     * Create an application and get the name from zeidon.app.
     *
     * @param zeidonRootDir
     */
    ApplicationImpl( String zeidonRootDir, ObjectEngine objectEngine )
    {
        this.zeidonRootDir = zeidonRootDir;
        this.objectEngine = objectEngine;
    }

    @Override
    public String getName()
    {
        return attributes.get( "APP_NAME" );
    }

    @Override
    public String getObjectDir()
    {
        return attributes.get( "APP_ADOBIN" );
    }

    @Override
    public synchronized LodDef getLodDef(TaskQualification taskQual, String name) throws UnknownLodDefException
    {
        String lowerName = name.toLowerCase();
        if ( lodDefList.containsKey( lowerName ))
            return lodDefList.get( lowerName );

        LodDef lodDef = new LodDef( taskQual.getTask(), this, name );
        lodDefList.put( lowerName, lodDef );

        return lodDef;
    }

    @Override
    public void setAttribute(PortableFileReader reader )
    {
        String attName = reader.getAttributeName();

        // If the attr name begins with ZEIDON then it's part of the system app and we can
        // ignore those attributes.
        if ( attName.startsWith( "ZEIDON" ) )
            return;

        String value = reader.getAttributeValue();
        if ( attName.equalsIgnoreCase("APP_ADOBIN") && StringUtils.isNotBlank(zeidonRootDir) )
        {
            value = FileSystems.getDefault().getPath( zeidonRootDir + "/" + value ).normalize().toAbsolutePath().toString();
        }
        attributes.put( attName, value );
    }

    @Override
    public void dropNameForView(String name, View view)
    {
        viewNameList.dropNameForView( name, view );
    }

    @Override
    public View getViewByName(String name)
    {
        return viewNameList.getViewByName( name );
    }

    @Override
    public void setNameForView(String name, View view)
    {
        // Move the view to be under the system task.  We do this because when
        // the view's current task is dropped this view will still hold on to
        // a reference to the task and it won't get cleaned up.
        if ( view.getTask() != view.getTask().getSystemTask() ) // Already system task?
        {
            ViewImpl v = ((InternalView) view ).getViewImpl();
            v.reassignTask( view.getTask().getSystemTask() );
        }

        viewNameList.setNameForView( name, view );
    }

    @Override
    public Collection<String> getAllViewNames(View view)
    {
        return viewNameList.getAllViewNames( view );
    }

    @Override
    public String toString()
    {
        return getName();
    }

    ViewNameList getViewNameList()
    {
        return viewNameList;
    }

    synchronized void loadDomains( Task task )
    {
        if ( domainList != null )
            return;  // Already loaded the domains.

        domainList = new DomainList( this );
        domainList.loadDomains( task );
   }

	@Override
	public Domain getDomain(String name)
	{
		return domainList.getDomain(name);
	}

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.Application#getPackage()
     */
    @Override
    public String getPackage()
    {
        return attributes.get( "APP_PACKAGE" );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.Application#nullStringEqualsEmptyString()
     */
    @Override
    public boolean nullStringEqualsEmptyString()
    {
        return true;  //TODO: Shouldn't this be read from config?
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.Application#getAllNamedViews()
     */
    @Override
    public Collection<? extends View> getAllNamedViews()
    {
        return getViewNameList().getAllNamedViews();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.Application#dropView(com.quinsoft.zeidon.View)
     */
    @Override
    public void dropView( View view )
    {
        getViewNameList().dropView( view );
    }

    @Override
    public boolean isSystemApp()
    {
        return false;
    }

    @Override
    public List<String> getLodNameList( Task task )
    {
        ClassLoader loader = this.getClass().getClassLoader();
        final String resourceDir = getObjectDir() + "/";
        Pattern pattern = Pattern.compile( "(.*)(\\.xod$)", Pattern.CASE_INSENSITIVE );

        File file = new File( resourceDir );
        if ( file.exists() )
        {
            try
            {
                return Files.list( file.toPath() )
                    .map( resourceName -> pattern.matcher( resourceName.toString() ) ) // Create a matcher
                    .filter( matcher -> matcher.matches() )                 // Keep only ones that match.
                    .map( matcher -> matcher.group( 1 ) ) // Get the base filename.
                    .map( path -> FilenameUtils.getBaseName(path) )
                    .collect( Collectors.toList() );
            }
            catch ( Exception e )
            {
                throw ZeidonException.wrapException( e );
            }
        }

        try
        {
            return IOUtils.readLines( loader.getResourceAsStream( resourceDir ), StandardCharsets.UTF_8)
                   .stream()
                   .map( resourceName -> pattern.matcher( resourceName ) ) // Create a matcher
                   .filter( matcher -> matcher.matches() )                 // Keep only ones that match.
                   .map( matcher -> matcher.group( 1 ) )                   // Get the base filename.
                   .collect( Collectors.toList() );
        }
        catch ( IOException e )
        {
            throw ZeidonException.wrapException( e ).appendMessage( "XOD resource dir: %s", resourceDir );
        }
    }

    @Override
    public synchronized CacheMap getCacheMap()
    {
        if ( cacheMap == null )
            cacheMap = new CacheMapImpl();

        return cacheMap;
    }

    @Override
    public ObjectEngine getObjectEngine()
    {
        return objectEngine;
    }
}
