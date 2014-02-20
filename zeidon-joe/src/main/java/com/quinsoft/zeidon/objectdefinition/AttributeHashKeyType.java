/**
 * 
 */
package com.quinsoft.zeidon.objectdefinition;

/**
 * Defines the types of attribute hashes.
 * 
 * @author DG
 *
 */
public enum AttributeHashKeyType
{
    /**
     * Attribute is not a hash key.
     */
    NONE,
    
    /**
     * Attribute is a global hash key which means that every entity in the OI
     * has a unique value for this attribute.  This attribute can be used to create a 
     * fast look-up map for cursor.setFirst() processing.
     */
    GLOBAL,
    
    /**
     * Attribute is unique within its parent only.
     */
    UNDER_PARENT
}
