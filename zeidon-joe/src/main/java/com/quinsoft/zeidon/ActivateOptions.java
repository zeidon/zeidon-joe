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
package com.quinsoft.zeidon;

import java.util.EnumSet;

import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.objectdefinition.LockingLevel;
import com.quinsoft.zeidon.objectdefinition.ViewOd;

/**
 * Encapsulates all activate options.
 *
 * @author DG
 *
 */
public class ActivateOptions extends AbstractOptionsConfiguration
{
    private ViewOd                 viewOd;
    private View                   qualificationObject;
    private EnumSet<ActivateFlags> activateFlags;
    private LockingLevel           lockingLevel = LockingLevel.NONE;

    /**
     * If not null then Activate processing will use this Activator instead
     * of using oiSourceUrl.
     */
    private Activator activator;

    /**
     * The name of this qualification.  Some day this could be used for calling
     * stored procedures or something with the FileDB.
     */
    private String                 qualificationName;

    public ActivateOptions( TaskQualification task )
    {
        super( task.getTask() );
        activateFlags = EnumSet.of( ActivateFlags.fMULTIPLE );
    }

    public ActivateOptions( View view )
    {
        super( view.getTask() );
        activateFlags = EnumSet.of( ActivateFlags.fMULTIPLE );
        viewOd = view.getViewOd();
    }

    /**
     * @return the activateFlags
     */
    public EnumSet<ActivateFlags> getActivateFlags()
    {
        return activateFlags;
    }

    /**
     * @param activateFlags the activateFlags to set
     */
    public ActivateOptions setActivateFlags( EnumSet<ActivateFlags> activateFlags )
    {
        this.activateFlags = activateFlags;
        return this;
    }

    public ActivateOptions addActivateFlags( EnumSet<ActivateFlags> activateFlags )
    {
        this.activateFlags.addAll( activateFlags );
        return this;
    }

    public ActivateOptions addActivateFlag( ActivateFlags activateFlag )
    {
        this.activateFlags.add( activateFlag );
        return this;
    }

    public ActivateOptions addActivateFlag( ActivateFlags... activateFlags )
    {
        for ( ActivateFlags f : activateFlags )
            this.activateFlags.add( f );

        return this;
    }

    /**
     * @return the lockingLevel
     */
    public LockingLevel getLockingLevel()
    {
        return lockingLevel;
    }

    /**
     * @param lockingLevel the lockingLevel to set
     */
    public ActivateOptions setLockingLevel( LockingLevel lockingLevel )
    {
        this.lockingLevel = lockingLevel;
        return this;
    }

    /**
     * @return the qualificationObject
     */
    public View getQualificationObject()
    {
        return qualificationObject;
    }

    /**
     * @param qualificationObject the qualificationObject to set
     */
    public ActivateOptions setQualificationObject( View qualificationObject )
    {
        this.qualificationObject = qualificationObject;
        return this;
    }

    /**
     * @return the viewOd
     */
    public ViewOd getViewOd()
    {
        return viewOd;
    }

    /**
     * @param viewOd the viewOd to set
     */
    public ActivateOptions setViewOd( ViewOd viewOd )
    {
        this.viewOd = viewOd;
        setLockingLevel( viewOd.getLockingLevel() );
        return this;
    }

    public ActivateOptions setViewOd( TaskQualification taskQual, String viewOdName )
    {
        setViewOd( taskQual.getApplication().getViewOd( taskQual, viewOdName ) );
        return this;
    }

    public ActivateOptions setViewOd( TaskQualification taskQual, Application app, String viewOdName )
    {
        setViewOd( app.getViewOd( taskQual, viewOdName ) );
        return this;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("Options: ViewOD = ").append( viewOd.getName() );
        sb.append( " Flags = " ).append( activateFlags );
        if ( qualificationObject != null )
            sb.append( " Qual: Yes" );
        if ( lockingLevel != LockingLevel.NONE )
            sb.append( " LockingLevel = " ).append( lockingLevel );

        return sb.toString();
    }

    /**
     * This will return the qualification name or generate one if it hasn't been set.
     * The generated name has the form:
     *
     *  VIEWOD_serialized-qualification.
     *
     * @return
     */
    public String generateQualificationName()
    {
        if ( StringUtils.isBlank( qualificationName ) )
        {
            StringBuilder builder = new StringBuilder( viewOd.getName() );
            builder.append( "_" );
            if ( qualificationObject == null )
                builder.append( "ALL_ENTITIES" );
            else
            {
                View v = qualificationObject.newView();
                v.cursor( "EntitySpec" ).setFirst();
                for ( EntityInstance qualAttrib : v.cursor( "QualAttrib" ).eachEntity() )
                {
                    String entityName = qualAttrib.getAttribute( "EntityName" ).getString();
                    if ( ! StringUtils.isBlank( entityName ) )
                        builder.append( entityName ).append( "." );

                    String attribName = qualAttrib.getAttribute( "AttributeName" ).getString();
                    if ( ! StringUtils.isBlank( attribName ) )
                        builder.append( attribName );

                    String oper = qualAttrib.getAttribute( "Oper" ).getString();
                    if ( ! StringUtils.isBlank( oper ) )
                        builder.append( "_" ).append( oper ).append( "_" );

                    String value = qualAttrib.getAttribute( "Value" ).getString();
                    if ( ! StringUtils.isBlank( value ) )
                        builder.append( "_" ).append( value ).append( "_" );
                }
            }

            qualificationName = builder.toString();

            // Let's get rid of duplicate underscores.
            qualificationName = qualificationName.replaceAll( "__", "_" );
        }

        return qualificationName;
    }

    public String getQualificationName()
    {
        return qualificationName;
    }

    public void setQualificationName( String qualificationName )
    {
        this.qualificationName = qualificationName;
    }

    @Override
    public ActivateOptions overrideConfigValue( String key, String value )
    {
        super.overrideConfigValue( key, value );
        return this;
    }

    @Override
    public Application getApplication()
    {
        return viewOd.getApplication();
    }

    public Activator getActivator()
    {
        return activator;
    }

    public void setActivator( Activator activator )
    {
        this.activator = activator;
    }
}
