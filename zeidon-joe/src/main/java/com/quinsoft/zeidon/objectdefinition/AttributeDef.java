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
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.standardoe.ScalaHelper;
import com.quinsoft.zeidon.utils.PortableFileReader;
import com.quinsoft.zeidon.utils.PortableFileReader.PortableFileAttributeHandler;

/**
 * @author DG
 *
 */
public class AttributeDef implements PortableFileAttributeHandler, Serializable
{
    private static final long serialVersionUID = 1L;
    private final EntityDef   entityDef;
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
    private   SourceFileType  derivedOperationsourceFileType = SourceFileType.VML;

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
    private EntityDef    hashKeyParent;
    private Boolean      isSequencingAscending = Boolean.TRUE;

    /**
     * If true then this attribute was created at runtime via EntityDef.createDynamicAttributeDef.
     */
    private boolean isDynamicAttribute = false;
    private DerivedOper derivedOperation;

    public AttributeDef(EntityDef entityDef)
    {
        super();
        this.entityDef = entityDef;
        attributeNumber = entityDef.getAttributeCount();
    }

    public AttributeDef(EntityDef entityDef, DynamicAttributeDefConfiguration config )
    {
        this( entityDef );
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
                    entityDef.setAutoSeq( this );
                }
                break;

            case 'D':
                // DERIVEDC is the name of the Java class that declares the derived function.
                // Java only (e.g. not in the C OE).
                if ( reader.getAttributeName().equals( "DERIVEDC" ))
                {
                    derivedOperationClassName = reader.getAttributeValue().intern();
                    if ( ! derivedOperationClassName.contains( "." ) )
                        derivedOperationClassName = getApplication().getPackage() + "." + derivedOperationClassName;
                }
                else
                if ( reader.getAttributeName().equals( "DERIVEDF" ))
                {
                    derivedOperationName = reader.getAttributeValue().intern();
                }
                else
                if ( reader.getAttributeName().equals( "DRSRCTYPE" ))
                {
                    derivedOperationsourceFileType = SourceFileType.parse( reader.getAttributeValue() );
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
                    entityDef.setGenKey( this );
                    entityDef.getLodDef().setHasGenKey( true );
                }
                break;

            case 'H':
                if ( reader.getAttributeName().equals( "HASHKEY" ))
                {
                    if ( hashKeyParent == null )
                        hashKeyParent = getEntityDef().getParent();

                    hashKeyType = AttributeHashKeyType.valueOf( reader.getAttributeValue() );
                    if ( hashKeyType != AttributeHashKeyType.NONE )
                        entityDef.addHashKeyAttribute( this );
                }
                else
                if ( reader.getAttributeName().equals( "HASHKEY_PARENT" ))
                {
                    String entityName = reader.getAttributeValue();
                    for ( hashKeyParent = getEntityDef().getParent();
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
                    entityDef.setHasInitializedAttributes( true );
                }

            case 'K':
                if ( reader.getAttributeName().equals( "KEY" ))
                {
                    key = true;
                    entityDef.addKey( this );
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
                    EntityDef search = entityDef;
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

    private Application getApplication()
    {
        return getEntityDef().getLodDef().getApplication();
    }

    AttributeDef setDomain( String domainName )
    {
        Application app = entityDef.getLodDef().getApplication();
        this.domainName = domainName;
        domain = app.getDomain( domainName );
        return this;
    }

    public EntityDef getEntityDef()
    {
        return entityDef;
    }

    AttributeDef setName( String name )
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

    AttributeDef setLength( int length )
    {
        this.length = length;
        return this;
    }

    @Override
    public String toString()
    {
        return entityDef.toString() + "." + name;
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

    public String getDerivedOperationClassName()
    {
        return derivedOperationClassName;
    }

    public String getDerivedOperationName()
    {
        return derivedOperationName;
    }

    private synchronized DerivedOper getDerivedOperation( Task task )
    {
        if ( derivedOperation != null )
            return derivedOperation;

        try
        {
            switch ( derivedOperationsourceFileType )
            {
                case VML:
                    derivedOperation = new VmlDerivedOper( task );
                    break;

                case JAVA:
                    derivedOperation = new JavaDerivedOper( task );
                    break;

                case SCALA:
                    derivedOperation = new ScalaDerivedOper( task );
                    break;

                default:
                    throw new ZeidonException( "Unsupported source file type for derived operations: %s",
                                               derivedOperationsourceFileType );
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
     * @param attributeInstance
     */
    public void executeDerivedAttributeForGet( AttributeInstance attributeInstance )
    {
        getDerivedOperation( attributeInstance.getTask() ).getDerivedValue( attributeInstance );
    }

    static private final Class<?>[] ARGUMENT_TYPES_VML     = new Class<?>[] { View.class, String.class, String.class, Integer.class };
    static private final Class<?>[] ARGUMENT_TYPES_JAVA    = new Class<?>[] { AttributeInstance.class };
    static private final Class<?>[] CONSTRUCTOR_ARG_VML    = new Class<?>[] { View.class };
    static private final Class<?>[] CONSTRUCTOR_ARG_JAVA   = new Class<?>[] { };

    /**
     * Call a derived operation.
     *
     * @author dg
     *
     */
    private abstract class DerivedOper
    {
        protected final Method method;
        protected final Constructor<? extends Object> constructor;

        private DerivedOper( Task task, Class<?>[] constructorArgs, Class<?>[] argumentTypes ) throws Exception
        {
            if ( constructorArgs != null )
            {
                ObjectEngine oe = task.getObjectEngine();
                ClassLoader classLoader = oe.getClassLoader( derivedOperationClassName );
                Class<? extends Object> operationsClass;
                operationsClass = classLoader.loadClass( derivedOperationClassName );

                constructor = operationsClass.getConstructor( constructorArgs );
                method = operationsClass.getMethod( derivedOperationName, argumentTypes );
            }
            else
            {
                constructor = null;
                method = null;
            }
        }

        private void getDerivedValue( AttributeInstance attributeInstance )
        {
            callDerivedOperation( attributeInstance );
        }

        abstract void callDerivedOperation( AttributeInstance attributeInstance );

        @Override
        public String toString()
        {
            return derivedOperationClassName + "." + derivedOperationName;
        }
    }

    /**
     * Call a derived operation as it's defined in Java generated from VML.
     */
    private class VmlDerivedOper extends DerivedOper
    {
        private VmlDerivedOper( Task task ) throws Exception
        {
            super( task, CONSTRUCTOR_ARG_VML, ARGUMENT_TYPES_VML );
        }

        @Override
        void callDerivedOperation( AttributeInstance attributeInstance )
        {
            try
            {
                View view = attributeInstance.getView();
                Object oper = constructor.newInstance( view );
                Object[] argList = new Object[] { view, getEntityDef().getName(), getName(), DERIVED_GET };
                method.invoke( oper, argList );
            }
            catch ( Throwable e )
            {
                throw ZeidonException.prependMessage( e, "Oper: %s", this )
                                     .prependAttributeDef( AttributeDef.this  );
            }
        }
    }

    /**
     * Call a derived operation as it's defined in hand-coded Java.
     */
    private class JavaDerivedOper extends DerivedOper
    {
        private JavaDerivedOper( Task task ) throws Exception
        {
            super( task, CONSTRUCTOR_ARG_JAVA, ARGUMENT_TYPES_JAVA );
        }

        @Override
        void callDerivedOperation( AttributeInstance attributeInstance )
        {
            try
            {
                Object oper = constructor.newInstance( );
                Object[] argList = new Object[] { attributeInstance };
                method.invoke( oper, argList );
            }
            catch ( Throwable e )
            {
                throw ZeidonException.prependMessage( e, "Oper: %s", this )
                                     .prependAttributeDef( AttributeDef.this  );
            }
        }
    }

    /**
     * Call a derived operation using the ScalaHelper.
     *
     */
    private class ScalaDerivedOper extends DerivedOper
    {
        private ScalaDerivedOper( Task task ) throws Exception
        {
            super( task, null, null );
        }

        @Override
        void callDerivedOperation( AttributeInstance attributeInstance )
        {
            try
            {
                ScalaHelper scalaHelper = attributeInstance.getTask().getScalaHelper();
                scalaHelper.calculateDerivedAttribute( attributeInstance );
            }
            catch ( Throwable e )
            {
                throw ZeidonException.prependMessage( e, "Oper: %s", this )
                                     .prependAttributeDef( AttributeDef.this  );
            }
        }
    }

    public AttributeDef getNextAttributeDef()
    {
        int idx = getAttributeNumber() + 1;
        if ( idx == entityDef.getAttributeCount() )
            return null;

        return entityDef.getAttribute( idx );
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
    public EntityDef getHashKeyParent()
    {
        return hashKeyParent;
    }

    /**
     * Returns true if this AttributeDef is a sequencing attribute in ascending order, false
     * if it's descending, and null if it's not a sequencing attribute.  See
     * EntityDef.getSequencingAttributes() for more.
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
