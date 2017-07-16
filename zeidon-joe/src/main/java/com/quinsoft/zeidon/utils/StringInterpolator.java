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
package com.quinsoft.zeidon.utils;

import java.beans.FeatureDescriptor;
import java.util.Iterator;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.el.ValueExpression;

import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.objectdefinition.EntityDef;

import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.util.SimpleContext;


/**
 * Utility to perform string interpolation using Zeidon objects.
 *
 */
public class StringInterpolator
{
    private final ExpressionFactoryImpl factory;
    private final SimpleContext context;

    private Map<String, Object> variables;
    private Task task;
    private View view;

    public StringInterpolator( )
    {
        factory = new de.odysseus.el.ExpressionFactoryImpl();
        context = new de.odysseus.el.util.SimpleContext();
        context.setELResolver( new TaskResolver() );
    }

    public String interpolate( String string )
    {
        ValueExpression e = factory.createValueExpression(context, string, String.class);
        return e.getValue( context ).toString();
    }

    public StringInterpolator addVariables( Map<String, Object> vars )
    {
        variables.putAll( vars );
        return this;
    }

    public StringInterpolator addVariable( String variableName, Object value )
    {
        variables.put( variableName, value );
        return this;
    }

    public StringInterpolator setVariables( Map<String, Object> variables )
    {
        this.variables = variables;
        return this;
    }

    public StringInterpolator setTask( Task task )
    {
        this.task = task;
        return this;
    }

    public StringInterpolator setView( View view )
    {
        this.view = view;
        return this;
    }

    /**
     * @param args
     */
    public static void main( String[] args )
    {
        StringInterpolator interpolator = new StringInterpolator().addVariable( "_foo", "xxx" );
        System.out.println( interpolator.interpolate( "Hello ${_foo}!" ) );
    }

    private class TaskResolver extends ELResolver
    {

        @Override
        public Object invoke( ELContext context, Object base1, Object method, Class<?>[] paramTypes, Object[] params )
        {
            System.out.println( "invoke" );
            // TODO Auto-generated method stub
            return super.invoke( context, base1, method, paramTypes, params );
        }

        @Override
        public Class<?> getCommonPropertyType( ELContext arg0, Object arg1 )
        {
            System.out.println( "getCommandPropertyType" );
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Iterator<FeatureDescriptor> getFeatureDescriptors( ELContext arg0, Object arg1 )
        {
            System.out.println( "getFeatureDescriptors" );
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Class<?> getType( ELContext arg0, Object arg1, Object arg2 )
        {
            System.out.println( "getType" );
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Object getValue( ELContext context, Object base, Object propertyObject )
        {
            String property = propertyObject.toString();
            System.out.println( "getValue " + property );

            context.setPropertyResolved( true );
            if ( base == null )
            {
                if ( variables != null && variables.containsKey( property ) )
                    return variables.get( property );

                if ( task != null )
                {
                    View v = task.getViewByName( property );
                    if ( v != null )
                        return v;
                }

                if ( view != null )
                {
                    EntityDef entityDef = view.getLodDef().getEntityDef( property, false );
                    if ( entityDef != null )
                        return view.cursor( entityDef );
                }
            }
            else if ( base instanceof View )
            {
                View view = (View) base;
                return view.cursor( property );
            }
            else if ( base instanceof EntityInstance )
            {
                EntityInstance ei = (EntityInstance) base;
                return ei.getAttribute( property ).getString();
            }

            context.setPropertyResolved( false );
            return null;
        }

        @Override
        public boolean isReadOnly( ELContext arg0, Object arg1, Object arg2 )
        {
            return true;
        }

        @Override
        public void setValue( ELContext context, Object base, Object property, Object value )
        {
            System.out.println( "setValue" );
        }
    }
}
