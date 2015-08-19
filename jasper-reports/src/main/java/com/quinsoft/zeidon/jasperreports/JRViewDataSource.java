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
package com.quinsoft.zeidon.jasperreports;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRRewindableDataSource;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JsonData;

import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.EntityDef;

/**
 * A wrapper to allow a Zeidon View to be used as a JasperReports DataSource.  This
 * class implements JsonData to allow SubReports to easily use subobjects.
 *
 */
public class JRViewDataSource implements JRDataSource, JRRewindableDataSource, JsonData
{
    // "((\\w*)(\\.))?(\\w*)\\s*(\\s*\\(\\s*(\\w*)\\s*\\)\\s*)?\\s*(\\s*\\>(.*))?"
    private final static String REGEX = "((\\w*)(\\.))?"                  // Optional entity name--word followed by period.
                                      + "(\\w*)"                          // Required attribute name.
                                      + "\\s*"                            // Optional whitespace
                                      + "(\\s*\\(\\s*(\\w*)\\s*\\)\\s*)?" // Optional context name--word inside parens.
                                      + "\\s*"                            // Optional whitespace
                                      + "(\\s*\\>(.*))?";                 // Optional default value--all chars after ">".
    private final static Pattern FIELD_PATTERN = Pattern.compile( REGEX );

    final protected View view;

    /**
     * This entity is the entity that we loop on.
     */
    final protected EntityDef loopEntity;
    final protected EntityDef scopingEntity;

    protected boolean cursorSet;

    /**
     * Empty constructor.  Intended to be called from a DataSourceProvider when testing
     * the provider from Jasper's Report Studio.
     */
    public JRViewDataSource( )
    {
        view = null;
        loopEntity = null;
        scopingEntity = null;
    }

    public JRViewDataSource( View view, JasperReport jasperReport )
    {
        this.view = view;
        this.loopEntity = getReportRoot( jasperReport );
        scopingEntity = getReportScoping( jasperReport );

        try
        {
            moveFirst();
        }
        catch ( JRException e )
        {
            throw ZeidonException.wrapException( e );
        }
    }


    protected JRViewDataSource( View view, EntityDef loopEntity, EntityDef scopingEntity )
    {
        this.view = view;
        this.loopEntity = loopEntity;
        this.scopingEntity = scopingEntity;
    }

    protected EntityDef getReportRoot( JasperReport jasperReport )
    {
        String reportRootStr = jasperReport.getProperty( "com.quinsoft.zeidon.reportRoot" );
        view.log().debug( "com.quinsoft.zeidon.reportRoot = %s", reportRootStr );
        if ( StringUtils.isBlank( reportRootStr ) )
            return view.getLodDef().getRoot();

        return view.getLodDef().getEntityDef( reportRootStr );
    }

    protected EntityDef getReportScoping( JasperReport jasperReport )
    {
        String scopingEntityStr = jasperReport.getProperty( "com.quinsoft.zeidon.scopingEntity" );
        view.log().debug( "com.quinsoft.zeidon.scopingEntity = %s", scopingEntityStr );
        if ( StringUtils.isBlank( scopingEntityStr ) )
            return null;

        return view.getLodDef().getEntityDef( scopingEntityStr );
    }

    /* (non-Javadoc)
     * @see net.sf.jasperreports.engine.JRRewindableDataSource#moveFirst()
     */
    @Override
    public void moveFirst() throws JRException
    {
        cursorSet = false;
    }

    /* (non-Javadoc)
     * @see net.sf.jasperreports.engine.JRDataSource#getFieldValue(net.sf.jasperreports.engine.JRField)
     */
    @Override
    public Object getFieldValue( JRField field ) throws JRException
    {
        Object value = get( field );
        view.log().info( "Get field %s ", field.getName() );
        return value;
    }

    private Object get( JRField field ) throws JRException
    {
        String fieldName = field.getName();
        Matcher m = FIELD_PATTERN.matcher( fieldName );
        if ( ! m.matches() )
            throw new ZeidonException( "FieldName value doesn't match expected pattern of "
                                     + "[ entity-name. ] attribute-name [ ( context-name ) ] [ > default ]" )
                      .appendMessage( "FieldName = %s", fieldName );

        String entityName = m.group( 2 );
        EntityDef entityDef = loopEntity;
        if ( ! StringUtils.isBlank( entityName ) )
            entityDef = view.getLodDef().getEntityDef( entityName );
        EntityCursor cursor = view.cursor( entityDef );

        String defaultValue = m.group( 8 );
        if ( defaultValue == null )
            defaultValue = null;

        if ( cursor.isNull() )
            return defaultValue;

        String attributeName = m.group( 4 );
        AttributeInstance attribute = cursor.getAttribute( attributeName );
        if ( attribute.isNull() )
            return defaultValue;

        String contextName = m.group( 6 );
        if ( ! StringUtils.isBlank( contextName ) )
            return attribute.getString( contextName );

        if ( field.getValueClass().equals( "java.lang.String" ) )
            return attribute.getString();

        return attribute.getValue();
    }

    /* (non-Javadoc)
     * @see net.sf.jasperreports.engine.JRDataSource#next()
     */
    @Override
    public boolean next() throws JRException
    {
        if ( ! cursorSet )
        {
            cursorSet = true;
            return view.cursor( loopEntity).setFirst( scopingEntity ).isSet();
        }

        boolean rc = view.cursor( loopEntity ).setNextContinue().isSet();
        if ( rc )
            view.log().info( "next() found %s", view.cursor( loopEntity ).getEntityInstance() );

        return rc;
    }

    @Override
    public JRViewDataSource subDataSource( )
    {
        throw new ZeidonException( "entitySelection for subDataSource is not supplied" );
    }

    /**
     * Return a data source that references a subobject for the current view.
     * EntitySelection is: "LOOPING-ENTITY[/SCOPING-ENTITY]".
     *
     * For example, given the following simple LOD structure:
     *   A
     *   |
     *   B
     *   |
     *   C
     *
     * If entitySelection is "C" then only C's under the current B will be
     * iterated.  For "C/A" then all C's under the current A will be iterated.
     */
    @Override
    public JRViewDataSource subDataSource( String entitySelection )
    {
        if ( StringUtils.isBlank( entitySelection ) )
            throw new ZeidonException( "entitySelection for subDataSource is not supplied" );

        String[] strings = entitySelection.split( "/" );

        EntityDef scopingEntity = null;
        if ( strings.length > 1 )
            scopingEntity = view.getLodDef().getEntityDef( strings[1] );

        EntityDef loopEntity = view.getLodDef().getEntityDef( strings[0] );

        return new JRViewDataSource( view.newView(), loopEntity, scopingEntity );
    }
}
