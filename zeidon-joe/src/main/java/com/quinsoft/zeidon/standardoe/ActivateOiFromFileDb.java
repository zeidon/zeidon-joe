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

import java.io.File;
import java.io.FileFilter;
import java.util.EnumSet;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.DeserializeOi;
import com.quinsoft.zeidon.ActivateOptions;
import com.quinsoft.zeidon.Activator;
import com.quinsoft.zeidon.CursorResult;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.ViewAttribute;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.objectdefinition.ViewOd;

/**
 * Activates an OI from a Zeidon file DB, which is OIs stored in a directory.
 *
 * @author dgc
 *
 */
public class ActivateOiFromFileDb implements Activator
{
    private TaskImpl        task;
    private ViewImpl        view;
    private View            qual;
    private EnumSet<ActivateFlags> control;
    private ViewOd          viewOd;
    private ActivateOptions options;
    private EntityDef      rootEntityDef;
    private ViewAttribute   qualViewAttrib;
    private String          qualValue;
    private FileDbUtils     fileDbUtils;

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.Activator#init(com.quinsoft.zeidon.Task, com.quinsoft.zeidon.View, com.quinsoft.zeidon.ActivateOptions)
     */
    @Override
    public View init( Task task, View initialView, ActivateOptions options )
    {
        assert options != null;

        this.task = (TaskImpl) task;
        if ( initialView == null )
            view = (ViewImpl) task.activateEmptyObjectInstance( options.getViewOd() );
        else
            view = ((InternalView) initialView).getViewImpl();

        this.qual = options.getQualificationObject();
        this.options = options;
        control = options.getActivateFlags();
        viewOd = options.getViewOd();
        fileDbUtils = new FileDbUtils( options );

        return view;
    }

    /**
     * Validate that the qual object is well-formed for File DB and parse out the entityDef,
     * viewAttrib, and qual value.
     */
    private void validateQual()
    {
        if ( qual == null )
            return;

        EntityCursor entitySpec = qual.cursor( "EntitySpec" );
        if ( entitySpec.getEntityCount() > 1 )
            throw new ZeidonException( "File DB supports qualification on the root only" );

        entitySpec.setFirst();
        String entityName = entitySpec.getAttribute( "EntityName" ).getString();
        rootEntityDef = viewOd.getEntityDef( entityName );
        if ( ! rootEntityDef.equals( viewOd.getRoot() ) )
            throw new ZeidonException( "File DB supports qualification on the root only" );

        EntityCursor qualAttrib = qual.cursor( "QualAttrib" );
        if ( qualAttrib.getEntityCount() != 1 )
            throw new ZeidonException( "File DB supports qualification on one and only one attribute." );

        entityName = qualAttrib.getAttribute( "EntityName" ).getString();
        if ( ! rootEntityDef.getName().equals( entityName ) )
            throw new ZeidonException( "File DB supports qualification on the root only" );

        String attribName = qualAttrib.getAttribute( "AttributeName" ).getString();
        if ( StringUtils.isBlank( attribName ) )
            throw new ZeidonException( "File DB requires qualification on an attribute." );

        qualViewAttrib = rootEntityDef.getAttribute( attribName );

        String oper = qualAttrib.getAttribute( "Oper" ).getString();
        if ( ! StringUtils.equals( oper, "=" ) )
            throw new ZeidonException( "File DB only supports '=' for qualification comparison" );

        qualValue = qualAttrib.getAttribute( "Value" ).getString();
        if ( StringUtils.isBlank( qualValue ) )
            throw new ZeidonException( "File DB qualification requires a comparison value" );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.Activator#activate()
     */
    @Override
    public View activate()
    {
        // If the oiSourceUrl specifies a file name then use that.
        if ( fileDbUtils.urlIsFile() )
            return activateFile( fileDbUtils.getDirectoryName() );

        if ( ! StringUtils.isBlank( options.getQualificationName() ) )
            return activateByQualificationName();

        if ( qual == null )
            return activateFile( fileDbUtils.genFilename( viewOd, "ALL_DATA" ) );

        validateQual();
        if ( qualViewAttrib.isKey() )
        {
            String filename = fileDbUtils.genFilename( viewOd, fileDbUtils.genKeyQualifier( qualViewAttrib, qualValue ) );
            return activateFile( filename );
        }

        // If we get here then the qualification is on a non key and we'll have to activate
        // the OIs to find the right one.
        return performScan();
    }

    /**
     * Activates each of the files in the directory that have the same ViewOd until if
     * finds the right one.
     *
     * @return
     */
    private View performScan()
    {
        task.dblog().debug( "FileDB: performing scan of %s", fileDbUtils.getDirectoryName() );
        File dir = new File( fileDbUtils.getDirectoryName() );
        FileFilter fileFilter = new WildcardFileFilter( viewOd.getName() + "*" + fileDbUtils.getStreamFormat().getExtension() );
        File[] files = dir.listFiles( fileFilter );
        for ( File file : files )
        {
            task.dblog().debug( "Loading %s and checking for match", file.getAbsoluteFile() );
            View v = activateFile( file.getAbsolutePath() );
            if ( v.cursor( viewOd.getRoot() ).setFirst( qualViewAttrib, qualValue ) == CursorResult.SET )
            {
                task.dblog().debug( "Got a match!" );
                return v;
            }
        }

        // We didn't find a match so return the original empty view.
        return view;
    }

    private View activateFile( final String filename )
    {
        task.dblog().info( "Reading OI from %s", filename );
        return new DeserializeOi( task )
                        .fromResource( filename )
                        .setFormat( fileDbUtils.getStreamFormat() )
                        .activateFirst();
    }

    private View activateByQualificationName()
    {
        String filename = fileDbUtils.genFilename( viewOd,  options.getQualificationName() );
        return activateFile( filename );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.Activator#activate(com.quinsoft.zeidon.objectdefinition.EntityDef)
     */
    @Override
    public int activate( EntityDef subobjectRootEntity )
    {
        throw new ZeidonException( "Lazy-load activates are not supported by File DB Handler" );
    }
}
