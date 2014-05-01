/**
 *
 */
package com.quinsoft.zeidon.scala

/**
 * @author dgc
 *
 */
object AttributeInstance {
    implicit def attributeInstance2String( attr: AttributeInstance ) = attr.jattributeInstance.getString( attr.contextName )
    implicit def attributeInstance2Int( attr: AttributeInstance ): Integer = attr.jattributeInstance.getInteger( attr.contextName )
    implicit def attributeInstance2Double( attr: AttributeInstance ): Double = attr.jattributeInstance.getDouble( attr.contextName )
}

class AttributeInstance( val jattributeInstance: com.quinsoft.zeidon.AttributeInstance ) {
    var contextName: String = null

    def isNull = jattributeInstance.isNull()
    
    override def equals(other: Any) = other match {
        // If 'other' is an AttributeInstance, get its value before calling compare.
        case attr: AttributeInstance => jattributeInstance.compare(attr.jattributeInstance.getInternalAttributeValue()) == 0

        // Default case: call compare with the value.
        case _ => jattributeInstance.compare(other) == 0
    }

    override def toString = jattributeInstance.getString()
}