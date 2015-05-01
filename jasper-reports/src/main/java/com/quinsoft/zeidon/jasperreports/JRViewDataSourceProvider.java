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

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRDataSourceProvider;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRQuery;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignField;

import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.LodDef;
import com.quinsoft.zeidon.standardoe.DefaultJavaOeConfiguration;
import com.quinsoft.zeidon.standardoe.JavaObjectEngine;
import com.quinsoft.zeidon.utils.QualificationBuilder;

/**
 *
 */
public abstract class JRViewDataSourceProvider implements JRDataSourceProvider
{
    private ObjectEngine objectEngine = null;

    public JRViewDataSourceProvider()
    {
    }

    /**
     * Gets the View for this datasource provider.
     *
     * @return
     */
    protected View getReportView( JasperReport report )
    {
        info( "getReportView" );

        String appName = getAppName( report );

        String lodName = getLodName( report );

        ObjectEngine oe = getObjectEngine();
        Task task = oe.createTask( appName );
        JRQuery query = report.getQuery();
        String qualJson = query.getText();
        if ( StringUtils.isBlank( qualJson ) )
            throw new ZeidonException( "JSON query text has not be specified" );

        info( "qual json = \n%s", qualJson );

        QualificationBuilder qual = new QualificationBuilder( task  );
        View view = qual.setLodDef( lodName )
                        .loadFromSerializedString( qualJson )
                        // Cache the results so we don't activate it again.
//                        .cachedAs( Integer.toString( lodName.hashCode() ) )
                        .activate();

//        qual.getView().serializeOi().toFile( "/tmp/qual.json" );
        view.serializeOi().asJson().toTempDir( "jasper" + lodName );
//        view.logObjectInstance();

        return view;
    }

    protected ObjectEngine getObjectEngine()
    {
        if ( objectEngine == null )
            objectEngine = new JavaObjectEngine( ( new DefaultJavaOeConfiguration() ) );

        return objectEngine;
    }

    /* (non-Javadoc)
     * @see net.sf.jasperreports.engine.JRDataSourceProvider#supportsGetFieldsOperation()
     */
    @Override
    public boolean supportsGetFieldsOperation()
    {
        return true;
    }

    /* (non-Javadoc)
     * @see net.sf.jasperreports.engine.JRDataSourceProvider#getFields(net.sf.jasperreports.engine.JasperReport)
     */
    @Override
    public JRField[] getFields( JasperReport report ) throws JRException, UnsupportedOperationException
    {
        if ( report == null )
            return new JRField[0];
        
        ObjectEngine oe = getObjectEngine();
        info( "JRViewDataSourceProvider.getFields" );

        String appName = getAppName( report );
        String lodName = getLodName( report );

        Application app = oe.getApplication( appName );
        LodDef lodDef = app.getLodDef( oe.getSystemTask(), lodName );
        List<JRField> attributes = new ArrayList<>();
        for ( AttributeDef attr : lodDef.getRoot().getAttributes() )
        {
            if ( attr.isHidden() )
                continue;

            JRDesignField field = new JRDesignField();
            field.setName( attr.getName() );
            field.setValueClass( String.class );
            field.setDescription( "Entity = " + attr.getEntityDef().getName() );
            attributes.add( field );
        }

        info( "Fields = %s", attributes );
        return attributes.toArray(new JRField[ attributes.size() ] );
    }

    protected void info( String format, Object...args )
    {
        getObjectEngine().getSystemTask().log().info( format, args );
    }

    /* (non-Javadoc)
     * @see net.sf.jasperreports.engine.JRDataSourceProvider#create(net.sf.jasperreports.engine.JasperReport)
     */
    @Override
    public JRDataSource create( JasperReport report ) throws JRException
    {
        // Report should be null only when executing "Test" button in Data Adapters.
        if ( report == null )
            return new JRViewDataSource();

        View view = getReportView( report );
        return new JRViewDataSource( view, report );
    }

    /* (non-Javadoc)
     * @see net.sf.jasperreports.engine.JRDataSourceProvider#dispose(net.sf.jasperreports.engine.JRDataSource)
     */
    @Override
    public void dispose( JRDataSource dataSource ) throws JRException
    {
        info( "JRViewDataSourceProvider.dispose" );
    }

    protected String getLodName( JasperReport report )
    {
        String lodName = report.getProperty( "com.quinsoft.zeidon.lodname" );
        info( "com.quinsoft.zeidon.lodname = %s", lodName );
        if ( StringUtils.isBlank( lodName ) )
            throw new ZeidonException( "LOD name is not specified" );

        return lodName;
    }

    protected String getAppName( JasperReport report )
    {
        String appName = report.getProperty( "com.quinsoft.zeidon.appname" );
        if ( StringUtils.isBlank( appName ) )
            throw new ZeidonException( "App name is not specified" );

        info( "com.quinsoft.zeidon.appname = %s", appName );
        return appName;
    }
}
