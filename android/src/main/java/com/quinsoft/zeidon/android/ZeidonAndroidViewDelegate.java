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
/**
 *
 */
package com.quinsoft.zeidon.android;

import org.apache.commons.lang3.StringUtils;

import android.util.AttributeSet;
import android.view.ViewGroup;

import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;

/**
 * This implements common logic used by Zeidon android views.
 *
 * @author dgc
 *
 */
public class ZeidonAndroidViewDelegate
{
    /**
     * This is the namespace for zeidon in the Android layout XML.
     */
    final static String ZEIDON_NS = "http://zeidon.quinsoft.com";

    private android.view.View androidView;
    private android.view.View parentView;
    private View   mappingView;
    private String viewName;
    private String entityName;
    private String attributeName;
    private String contextName;
    private String nullRepresentation;

    /**
     * The entityInstance which is the source of the data for androidView.
     */
    private EntityInstance entityInstance;

    /**
     * Load attribute values common to most/all Zeidon views.
     *
     * @param androidView
     * @param attrs
     */
    public void loadConfig( android.view.View androidView, AttributeSet attrs )
    {
        this.androidView = androidView;
        viewName = attrs.getAttributeValue( ZEIDON_NS, "view_name" );
        entityName = attrs.getAttributeValue( ZEIDON_NS, "entity_name" );
        attributeName = attrs.getAttributeValue( ZEIDON_NS, "attribute_name" );
        contextName = attrs.getAttributeValue( ZEIDON_NS, "context_name" );
        nullRepresentation = attrs.getAttributeValue( ZEIDON_NS, "null_representation" );
    }

    /**
     * Get the task qualification for this view.  If it is not specified for this view then
     * check parent views.
     *
     * @return
     */
    public TaskQualification findTaskQualification()
    {
        try
        {
            return findTaskQualification( androidView );
        }
        catch ( Exception e )
        {
            throw AndroidUtils.appendViewInfo( e, androidView );
        }
    }

    public TaskQualification findTaskQualification( android.view.View av )
    {
        while ( av != null )
        {
            if ( av instanceof ZeidonTaskQualifier )
            {
                ZeidonTaskQualifier qualifier = (ZeidonTaskQualifier) av;
                if ( qualifier.getTaskQualification() != null )
                    return qualifier.getTaskQualification();
            }

            if ( av instanceof ZeidonDisplayView )
            {
                ZeidonDisplayView displayView = (ZeidonDisplayView) av;
                if ( displayView.getZeidonParent() != null )
                {
                    av = displayView.getZeidonParent();
                    continue;
                }
            }

            av = (android.view.View) av.getParent();
        }

        throw new ZeidonException( "Zeidon Android view does not have a view_name specified anywhere in the parent chain." );
    }

    /**
     * Determines which entity instance is the source of the data for the androidView
     * associated with this delegate.  It is then used when setting/gettting attribute
     * values.
     */
    public void setEntityInstance()
    {
        entityInstance = getMappingEntityCursor().getEntityInstance();
    }

    /**
     * Loop through all the children of viewGroup and copy values from an OI to the view.
     *
     * @param viewGroup
     */
    public void setChildrenFromOi( ViewGroup viewGroup )
    {
        for ( int i = 0; i < viewGroup.getChildCount(); i++ )
        {
            android.view.View child = viewGroup.getChildAt( i );
            if ( child instanceof ZeidonDisplayView )
                ((ZeidonDisplayView) child).setFromOi();
            else
            if ( child instanceof ViewGroup )
                setChildrenFromOi( (ViewGroup) child );
        }
    }

    /**
     * Loop through all the children of viewGroup and copy values from the Android
     * views to the OI.
     *
     * @param viewGroup
     */
    public void copyValuesToOi( ViewGroup viewGroup )
    {
        for ( int i = 0; i < viewGroup.getChildCount(); i++ )
        {
            android.view.View child = viewGroup.getChildAt( i );
            if ( child instanceof ZeidonInputView )
                ((ZeidonInputView) child).copyValuesToOi();
            else
            if ( child instanceof ViewGroup )
                copyValuesToOi( (ViewGroup) child );
        }
    }

    private String findViewName( android.view.View av )
    {
        android.view.View temp = av;
        while ( temp != null )
        {
            if ( temp instanceof ZeidonDisplayView )
            {
                ZeidonDisplayView displayView = (ZeidonDisplayView) temp;
                if ( displayView.getViewName() != null )
                    return displayView.getViewName();

                if ( displayView.getZeidonParent() != null )
                {
                    temp = displayView.getZeidonParent();
                    continue;
                }
            }

            temp = (android.view.View) temp.getParent();
        }

        throw new ZeidonException( "Zeidon Android view does not have a view_name specified anywhere in the parent chain." );
    }

    public String findViewName()
    {
        try
        {
            return findViewName( androidView );
        }
        catch ( Exception e )
        {
            throw AndroidUtils.appendViewInfo( e, androidView )
                              .appendMessage( "View name: ", viewName );
        }
    }

    /**
     * Get the view name for this.androidView.  If it isn't specified then check its parent.
     *
     * @return
     */
    public String getViewName()
    {
        return viewName;
    }

    private String findEntityName( android.view.View temp )
    {
        while ( temp != null )
        {
            if ( temp instanceof ZeidonDisplayView )
            {
                ZeidonDisplayView displayView = (ZeidonDisplayView) temp;
                if ( displayView.getEntityName() != null )
                    return displayView.getEntityName();

                if ( displayView.getZeidonParent() != null )
                {
                    temp = displayView.getZeidonParent();
                    continue;
                }
            }

            temp = (android.view.View) temp.getParent();
        }

        throw new ZeidonException( "Zeidon Android view does not have an entity_name specified anywhere in the parent chain." );
    }

    public String findEntityName()
    {
        try
        {
            return findEntityName( androidView );
        }
        catch ( Exception e )
        {
            throw AndroidUtils.appendViewInfo( e, androidView );
        }
    }

    /**
     * Get the entity name for this.androidView.
     *
     * @return
     */
    public String getEntityName( )
    {
        return entityName;
    }

    public String getAttributeName()
    {
        return attributeName;
    }

    public String getContextName()
    {
        return contextName;
    }

    private View getViewByName( android.view.View av )
    {
        TaskQualification taskQual = findTaskQualification();
        String tempViewName = findViewName();
        View view = taskQual.getViewByName( tempViewName );
        return view;
    }

    /**
     * Searches for the mapping view for the specified Android view.  If the mapping view
     * is not specified for the Android view then this will check the parents.
     *
     * @param av
     * @return
     */
    private View findMappingView( android.view.View av )
    {
        while ( av != null )
        {
            if ( av instanceof ZeidonDisplayView )
            {
                ZeidonDisplayView displayView = (ZeidonDisplayView) av;
                if ( displayView.getMappingView() != null )
                    return displayView.getMappingView();

                if ( displayView.getViewName() != null )
                    return getViewByName( av );

                if ( displayView.getZeidonParent() != null )
                {
                    av = displayView.getZeidonParent();
                    continue;
                }
            }

            av = (android.view.View) av.getParent();
        }

        throw new ZeidonException( "Couldn't find mapping Zeidon view." );
    }

    /**
     * Searches for the mapping view for the specified Android view.  If the mapping view
     * is not specified for the Android view then this will check the parents.
     *
     * @param av
     * @return
     */
    public View findMappingView( )
    {
        return findMappingView( androidView );
    }

    private View findView( android.view.View av )
    {
        while ( av != null )
        {
            if ( av instanceof ZeidonDisplayView )
            {
                ZeidonDisplayView displayView = (ZeidonDisplayView) av;
                if ( displayView.getViewName() != null )
                    return getViewByName( av );
            }

            av = (android.view.View) av.getParent();
        }

        throw new ZeidonException( "Couldn't find Zeidon view." );
    }

    /**
     * Gets the mapping view for the specified Android view.  If the mapping view
     * is not specified for the Android view then this will check the parents.
     *
     * @param av
     * @return
     */
    public View findView( )
    {
        return findView( androidView );
    }

    public EntityCursor getEntityCursor( )
    {
        View view = findView( );
        return view.cursor( findEntityName( ) );
    }

    public EntityCursor getMappingEntityCursor( )
    {
        View view = findMappingView( );
        return view.cursor( findEntityName( ) );
    }

    /**
     * Using view, entity, and attribute names defined for this androidView, get the
     * attribute value from the OI.  If the attribute value is null and the androidView
     * specifies null_representation, return that instead.
     *
     * @return
     */
    public String getStringFromAttribute( )
    {
        if ( StringUtils.isBlank( attributeName ) )
            throw new ZeidonException( "Zeidon Android view requires zeidon:attribute_name" );

        try
        {
            if ( entityInstance == null )
                throw new ZeidonException( "Delegate entityInstance is null.  Did you forget to call setEntityInstance()?" );

            if ( nullRepresentation != null && entityInstance.isAttributeNull( attributeName ) )
                return nullRepresentation;

            if ( StringUtils.isBlank( contextName ) )
                return entityInstance.getStringFromAttribute( attributeName );

            return entityInstance.getStringFromAttribute( attributeName, contextName );
        }
        catch ( Exception e )
        {
            throw AndroidUtils.appendViewInfo( e, androidView )
                              .appendMessage( "Attribute name: %s", attributeName );
        }
    }

    public void setAttribute( Object value )
    {
        if ( StringUtils.isBlank( attributeName ) )
            throw new ZeidonException( "Zeidon Android View requires zeidon:attribute_name" );

        try
        {
            if ( entityInstance == null )
                throw new ZeidonException( "Delegate entityInstance is null.  Did you forget to call setEntityInstance()?" );

            entityInstance.getAttribute( attributeName ).setValue( value );
        }
        catch ( Exception e )
        {
            throw AndroidUtils.appendViewInfo( e, androidView )
                              .appendMessage( "Attribute name: %s", attributeName );
        }
    }

    public void setParentView( android.view.View parentView )
    {
        this.parentView = parentView;
    }

    public android.view.View getParentView()
    {
        return parentView;
    }

    public void setParentViewForChildViews( android.view.View parent, android.view.View child )
    {
        if ( child instanceof ZeidonDisplayView )
        {
            ((ZeidonDisplayView) child).setZeidonParent( parent );
            return;
        }

        if ( child instanceof ViewGroup )
        {
            ViewGroup viewGroup = (ViewGroup) child;
            for ( int i = 0; i < viewGroup.getChildCount(); i++ )
            {
                android.view.View tempChild = viewGroup.getChildAt( i );
                setParentViewForChildViews( parent, tempChild );
            }
        }
    }

    /**
     * Returns the Zeidon mapping view for this delegate.  Does not attempt to check parents.
     * @return
     */
    public View getMappingView()
    {
        return mappingView;
    }

    public void setMappingView( View mappingView )
    {
        this.mappingView = mappingView;
    }
}
