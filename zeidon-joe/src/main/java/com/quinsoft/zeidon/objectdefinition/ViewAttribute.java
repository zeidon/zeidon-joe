/**
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
package com.quinsoft.zeidon.objectdefinition;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.utils.PortableFileReader;
import com.quinsoft.zeidon.utils.PortableFileReader.PortableFileAttributeHandler;

/**
 * @author DG
 *
 */
public class ViewAttribute implements PortableFileAttributeHandler, Serializable
{
    private static final long serialVersionUID = 1L;
    private final ViewEntity   viewEntity;
    private String       name;
    private Long         erAttributeToken;
    private InternalType type = InternalType.STRING;
    private Integer      length;

    /**
     * Used to match up DATARECORDS with CHILDENTITY lines in the XOD.
     */
    private int          xvaAttrToken;
    private String       initialValue;

    // Derived operation fields.
    public static final int DERIVED_SET = 1;
    public static final int DERIVED_GET = 2;

    // Following fields are protected instead of private to allow for quicker access when
    // reference by inner classes.
    protected String          derivedOperationName;
    protected String          derivedOperationClassName;

    private final ThreadLocal<DerivedOper> derivedOperations = new ThreadLocal<DerivedOper>();

    private AttributeHashKeyType hashKeyType = AttributeHashKeyType.NONE;

    /**
     * The unique number for the entity which is the index of the attribute for all
     * attributes.
     */
    private final int    attributeNumber;
    private boolean      hidden;
    private boolean      persistent;
    private boolean      key;
    private boolean      foreignKey;
    private boolean      autoSeq;
    private boolean      genKey;
    private boolean      update;
    private boolean      required;
    private boolean      debugChange;
    private Domain       domain;
    private String       domainName;
    private ViewEntity   hashKeyParent;
    private Boolean      isSequencingAscending = Boolean.TRUE;
    /**
     * If true then this attribute was created at runtime via ViewEntity.createDynamicViewAttribute.
     */
    private boolean isDynamicAttribute = false;

    public ViewAttribute(ViewEntity viewEntity)
    {
        super();
        this.viewEntity = viewEntity;
        attributeNumber = viewEntity.getAttributeCount();
    }

    public ViewAttribute(ViewEntity viewEntity, DynamicViewAttributeConfiguration config )
    {
        this( viewEntity );
        setName( config.getAttributeName() );
        setDomain( config.getDomainName() );
        setDynamicAttribute( true );

        // We'll set the ER attribute token to be a negative number to indicate
        // that it's not a normal token.
        erAttributeToken = (long) -attributeNumber;
    }

    @Override
    public void setAttribute(PortableFileReader reader)
    {
        String attributeName = reader.getAttributeName();

        switch ( attributeName.charAt( 0 ) )
        {
            case 'A':
                if ( reader.getAttributeName().equals( "AUTO_SEQ" ))
                {
                    autoSeq = true;
                    viewEntity.setAutoSeq( this );
                }
                break;

            case 'D':
                // DERIVEDC is the name of the Java class that declares the derived function.
                // Java only (e.g. not in the C OE).
                if ( reader.getAttributeName().equals( "DERIVEDC" ))
                {
                    derivedOperationClassName = reader.getAttributeValue().intern();
                }
                else
                if ( reader.getAttributeName().equals( "DERIVEDF" ))
                {
                    derivedOperationName = reader.getAttributeValue().intern();
                }
                else
                if ( reader.getAttributeName().equals( "DOMAIN" ))
                {
                    setDomain( reader.getAttributeValue().intern() );
                }
                else
                if ( reader.getAttributeName().equals( "DEBUGCHG" ))
                {
                    // requires commons-lang-2.4.jar or later
                    debugChange = StringUtils.startsWithIgnoreCase( reader.getAttributeValue(), "Y" );
                }
                break;

            case 'E':
                if ( reader.getAttributeName().equals( "ERATT_TOK" ))
                {
                    erAttributeToken = Long.parseLong( reader.getAttributeValue() );
                }
                break;

            case 'F':
                if ( reader.getAttributeName().equals( "FORKEY" ))
                {
                    foreignKey = reader.getAttributeValue().toUpperCase().startsWith( "Y" );
                }
                break;

            case 'G':
                if ( reader.getAttributeName().equals( "GENKEY" ))
                {
                    genKey = true;
                    viewEntity.setGenKey( this );
                    viewEntity.getViewOd().setHasGenKey( true );
                }
                break;

            case 'H':
                if ( reader.getAttributeName().equals( "HASHKEY" ))
                {
                    if ( hashKeyParent == null )
                        hashKeyParent = getViewEntity().getParent();

                    hashKeyType = AttributeHashKeyType.valueOf( reader.getAttributeValue() );
                    if ( hashKeyType != AttributeHashKeyType.NONE )
                        viewEntity.addHashKeyAttribute( this );
                }
                else
                if ( reader.getAttributeName().equals( "HASHKEY_PARENT" ))
                {
                    String entityName = reader.getAttributeValue();
                    for ( hashKeyParent = getViewEntity().getParent();
                          hashKeyParent != null;
                          hashKeyParent = hashKeyParent.getParent() )
                    {
                        if ( hashKeyParent.getName().equals( entityName ) )
                            break;
                    }

                    if ( hashKeyParent == null )
                        throw new ZeidonException("Unknown hashkey parent %s", entityName );
                }
                else
                if ( reader.getAttributeName().equals( "HIDDEN" ))
                {
                    hidden = reader.getAttributeValue().toUpperCase().startsWith( "Y" );
                }
                break;

            case 'I':
                if ( reader.getAttributeName().equals( "INIT" ))
                {
                    initialValue = reader.getAttributeValue();
                    viewEntity.setHasInitializedAttributes( true );
                }

            case 'K':
                if ( reader.getAttributeName().equals( "KEY" ))
                {
                    key = true;
                    viewEntity.addKey( this );
                }
                break;

            case 'L':
                if ( reader.getAttributeName().equals( "LTH" ))
                {
                    length = Integer.parseInt( reader.getAttributeValue() );
                }
                break;

            case 'N':
                if ( reader.getAttributeName().equals( "NAME" ))
                {
                    setName( reader.getAttributeValue().intern() );
                }
                break;

            case 'P':
                if ( reader.getAttributeName().equals( "PERSIST" ))
                {
                    persistent = reader.getAttributeValue().startsWith( "Y" );
                }
                break;

            case 'R':
                if ( reader.getAttributeName().equals( "REQUIRED" ))
                {
                    required = reader.getAttributeValue().startsWith( "Y" );
                }
                break;

            case 'S':
                if ( reader.getAttributeName().equals( "SEQUENCING" ))
                {
                    int position = Integer.parseInt( reader.getAttributeValue() );

                    // Find the first parent that can have multiple children.  If a parent has
                    // max cardinality of 1 then it can't be ordered.
                    ViewEntity search = viewEntity;
                    while ( search.getMaxCardinality() == 1 )
                        search = search.getParent();

                    search.addSequencingAttribute( this, position );
                }
                else
                if ( reader.getAttributeName().equals( "SEQ_AD" ))
                {
                    isSequencingAscending = reader.getAttributeValue().toUpperCase().startsWith( "A" );
                }
                break;

            case 'T':
                if ( reader.getAttributeName().equals( "TYPE" ))
                {
                    type = InternalType.mapCode( reader.getAttributeValue() );
                }
                break;

            case 'U':
                if ( reader.getAttributeName().equals( "UPDATE" ))
                {
                    update = reader.getAttributeValue().toUpperCase().startsWith( "Y" );
                }
                break;

            case 'X':
                if ( reader.getAttributeName().equals( "XVAATT_TOK" ))
                {
                    xvaAttrToken = Integer.parseInt( reader.getAttributeValue() );
                }
                break;
        }
    }

    ViewAttribute setDomain( String domainName )
    {
        Application app = viewEntity.getViewOd().getApplication();
        this.domainName = domainName;
        domain = app.getDomain( domainName );
        return this;
    }

    public ViewEntity getViewEntity()
    {
        return viewEntity;
    }

    ViewAttribute setName( String name )
    {
        this.name = name;
        return this;
    }

    public String getName()
    {
        return name;
    }

    public Long getErAttributeToken()
    {
        return erAttributeToken;
    }

    public InternalType getType()
    {
        return type;
    }

    /**
     * @return Returns max length of this attribute (if applicable). May return null
     *          in which case the max length defaults to the domain definition.
     */
    public Integer getLength()
    {
        return length;
    }

    ViewAttribute setLength( int length )
    {
        this.length = length;
        return this;
    }

    @Override
    public String toString()
    {
        return viewEntity.toString() + "." + name;
    }

    int getXvaAttrToken()
    {
        return xvaAttrToken;
    }

    public boolean isHidden()
    {
        return hidden;
    }

    public boolean isPersistent()
    {
        return persistent;
    }

    public boolean isRequired()
    {
        return required;
    }

    public int getAttributeNumber()
    {
        return attributeNumber;
    }

    /**
     * Loads the class that calls the derived operation from cache.  If the
     * object doesn't exist in the cache, create one.
     *
     * @param task
     * @return
     */
    private synchronized DerivedOper getDerivedOperation( Task task )
    {

        DerivedOper derivedOperation = derivedOperations.get();
        if ( derivedOperation == null )
        {
            derivedOperation = loadDerivedOperation( task );
            derivedOperations.set( derivedOperation );
        }

        return derivedOperation;
    }

    private DerivedOper loadDerivedOperation( Task view )
    {
        DerivedOper derivedOperation = null;

        try
        {
            ObjectEngine oe = view.getObjectEngine();
            ClassLoader classLoader = oe.getClassLoader( derivedOperationClassName );
            Class<? extends Object> operationsClass;
            operationsClass = classLoader.loadClass( derivedOperationClassName );

            Constructor<? extends Object> constructor;
            try
            {
                // First try getting a constructor that uses a view.  Most derived operations are defined
                // in the Object code so it must be created with a view.
                constructor = operationsClass.getConstructor( CONSTRUCTOR_ARG_TYPES1 );
            }
            catch ( NoSuchMethodException e )
            {
                // We couldn't find a constructor that takes a view, so try to find one that
                // takes a TaskQualification.  A derived attribute could be defined in a global
                // operations class, which uses a TaskQual for the constructor.
                try
                {
                    constructor = operationsClass.getConstructor( CONSTRUCTOR_ARG_TYPES2 );
                }
                catch ( NoSuchMethodException e2 )
                {
                    // One more time: try with no arguments.
                    constructor = operationsClass.getConstructor();
                }
            }

            // First try to find a method with the exact name of derivedOperationName.  This is the
            // old-style method as generated by VML.
            Method method;
            try
            {
                method = operationsClass.getMethod( derivedOperationName, ARGUMENT_TYPES1 );
                derivedOperation = new DerivedOper( constructor, method );
            }
            catch ( NoSuchMethodException e )
            {
                // Try with the JOE-specific parameter list.
                method = operationsClass.getMethod( derivedOperationName, ARGUMENT_TYPES2 );

                // If we get here then it worked!
                derivedOperation = new NewDerivedOper( constructor, method );
            }

            return derivedOperation;
        }
        catch( Exception e )
        {
            throw ZeidonException.prependMessage( e, "Error loading Derived operation '%s.%s' for %s",
                                                  derivedOperationClassName, derivedOperationName, toString() );
        }
    }

    public boolean isKey()
    {
        return key;
    }

    public boolean isForeignKey()
    {
        return foreignKey;
    }

    public boolean isAutoSeq()
    {
        return autoSeq;
    }

    public boolean isGenKey()
    {
        return genKey;
    }

    public boolean isUpdate()
    {
        return update;
    }

    public boolean isDerived()
    {
        return derivedOperationClassName != null;
    }

    public boolean isDebugChange()
    {
        return debugChange;
    }

    public Domain getDomain()
    {
        return domain;
    }

    /**
     * This calls the derived attribute function to set the internal attribute value.
     * The attribute value when then be retrieved by a get-attr method.
     *
     * @param view
     * @throws Exception
     */
    public void executeDerivedAttributeForGet( View view )
    {
        getDerivedOperation( view.getTask() ).getDerivedValue( view );
    }

    public void executeDerivedAttributeForGet( AttributeInstance attributeInstance )
    {
        getDerivedOperation( attributeInstance.getTask() ).getDerivedValue( attributeInstance );
    }

    static private final Class<?>[] ARGUMENT_TYPES1        = new Class<?>[] { View.class, String.class, String.class, Integer.class };
    static private final Class<?>[] ARGUMENT_TYPES2        = new Class<?>[] { AttributeInstance.class };
    static private final Class<?>[] CONSTRUCTOR_ARG_TYPES1 = new Class<?>[] { View.class };
    static private final Class<?>[] CONSTRUCTOR_ARG_TYPES2 = new Class<?>[] { TaskQualification.class };

    /**
     * Call a derived operation as it's defined in Java generated from VML.
     *
     * @author dg
     *
     */
    private class DerivedOper
    {
        protected final Method method;
        protected final Constructor<? extends Object> constructor;

        private DerivedOper( Constructor<? extends Object> constructor, Method method )
        {
            this.constructor = constructor;
            this.method = method;
        }

        private void getDerivedValue( View view )
        {
            callDerivedOperation( view );
        }

        private void getDerivedValue( AttributeInstance attributeInstance )
        {
            callDerivedOperation( attributeInstance );
        }

        void callDerivedOperation( View view )
        {
            try
            {
                Object oper = constructor.newInstance( view );
                Object[] argList = new Object[] { view, getViewEntity().getName(), getName(), DERIVED_GET };
                method.invoke( oper, argList );
            }
            catch ( Throwable e )
            {
                throw ZeidonException.prependMessage( e, "Oper: %s", this )
                                     .prependViewAttribute( ViewAttribute.this  );
            }
        }

        void callDerivedOperation( AttributeInstance attributeInstance )
        {
            callDerivedOperation( attributeInstance.getView() );
        }

        @Override
        public String toString()
        {
            return derivedOperationClassName + "." + derivedOperationName;
        }
    }

    /**
     * Call a derived operation as it's defined in native java.  Note that in this case
     * the Derived Object is instantiated as a singleton and reused.
     *
     * @author dg
     *
     */
    private class NewDerivedOper extends DerivedOper
    {
        private final Object oper;

        private NewDerivedOper( Constructor<? extends Object> constructor, Method method ) throws Exception
        {
            super( constructor, method );
            oper = constructor.newInstance();
        }

        @Override
        void callDerivedOperation( View view )
        {
            AttributeInstance ai = view.cursor( getViewEntity() ).getAttribute( ViewAttribute.this );
            callDerivedOperation( ai );
        }

        @Override
        void callDerivedOperation( AttributeInstance attributeInstance )
        {
            try
            {
                Object[] argList = new Object[] { attributeInstance };
                Object value = method.invoke( oper, argList );
                attributeInstance.setDerivedValue( value );
            }
            catch ( Throwable e )
            {
                throw ZeidonException.prependMessage( e, "Oper: %s", this )
                                     .prependViewAttribute( ViewAttribute.this  );
            }
        }
    }

    public ViewAttribute getNextViewAttribute()
    {
        int idx = getAttributeNumber() + 1;
        if ( idx == viewEntity.getAttributeCount() )
            return null;

        return viewEntity.getAttribute( idx );
    }

    /**
     * @return the initialValue
     */
    public String getInitialValue()
    {
        return initialValue;
    }

    /**
     * @return the hashKeyType
     */
    public AttributeHashKeyType getHashKeyType()
    {
        return hashKeyType;
    }

    /**
     * For attributes that are a hashkey under a parent, this returns the parent.
     * Otherwise returns null.
     *
     * @return
     */
    public ViewEntity getHashKeyParent()
    {
        return hashKeyParent;
    }

    /**
     * Returns true if this ViewAttribute is a sequencing attribute in ascending order, false
     * if it's descending, and null if it's not a sequencing attribute.  See
     * ViewEntity.getSequencingAttributes() for more.
     *
     * @return
     */
    public Boolean isSequencingAscending()
    {
        return isSequencingAscending;
    }

    public boolean isDynamicAttribute()
    {
        return isDynamicAttribute;
    }

    void setDynamicAttribute( boolean isDynamicAttribute )
    {
        this.isDynamicAttribute = isDynamicAttribute;
    }
}
