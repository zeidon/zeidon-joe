/**
 *
 */
package com.quinsoft.zeidon.scala

import scala.util.matching.Regex
import com.quinsoft.zeidon.ZeidonException

/**
 * @author dgc
 *
 */
object AttributeInstance {
    implicit def attributeInstance2String( attr: AttributeInstance ) = attr.jattributeInstance.getString
    implicit def attributeInstance2Int( attr: AttributeInstance ): Integer = attr.toInt
    implicit def attributeInstance2Double( attr: AttributeInstance ): Double = attr.toDouble
    implicit def attributeInstance2Boolean( attr: AttributeInstance ): Boolean = attr.toBoolean
}

class AttributeInstance( val jattributeInstance: com.quinsoft.zeidon.AttributeInstance ) {
    var contextName: String = null

    def isNull = jattributeInstance.isNull()
    def isEmpty = jattributeInstance.isNull() || jattributeInstance.compare( "" ) == 0
    def isUpdated = jattributeInstance.isUpdated()
    def setValue( any: Any ) = jattributeInstance.setValue( any )
    def setDerivedValue( any: Any ) = jattributeInstance.setDerivedValue( any )
    def value = jattributeInstance.getValue()
    def getString( contextName: String = null ) = jattributeInstance.getString( contextName )
    def name = jattributeInstance .getAttributeDef().getName()
    def attributeDef = jattributeInstance.getAttributeDef()

    /**
     * Compares a value to an attribute.  For an explanation on the distinction between
     * @>= and >= see the documentation on @==.
     */
    def @>=( other: Any ) = compare(other) >= 0

    /**
     * Compares a value to an attribute.  For an explanation on the distinction between
     * @<= and <= see the documentation on @==.
     */
    def @<=( other: Any ) = compare(other) <= 0

    /**
     * Compares a value to an attribute.  For an explanation on the distinction between
     * @> and > see the documentation on @==.
     */
    def @>( other: Any ) = compare(other) > 0

    /**
     * Compares a value to an attribute.  For an explanation on the distinction between
     * @< and < see the documentation on @==.
     */
    def @<( other: Any ) = compare(other) < 0

    /**
     * Compares a value to an attribute.  For an explanation on the distinction between
     * @!= and != see the documentation on @==.
     */
    def @!=( other: Any ) = compare(other) != 0

    /**
     * Compares a value to an attribute and returns true if they are equal.
     *
     * The difference between @== and == is that the left-hand value is not implicitly
     * converted before comparing when using @==.  For example, in this statement:
     *      if ( view.Entity.Attr == 0 )...
     * Scala will implicitly convert the attribute value to an integer and compare the
     * result to 0.  If the attribute is null this will throw an exception.
     *
     * The following statement will instead use Zeidon domain processing to compare the
     * 0 to the internal value of view.Entity.Attr.  If the attribute value is null
     * then the comparison will return false (assuming the domain allows comparing
     * a null value).
     *      if ( view.Entity.Attr @== 0 )...
     */
    def @==( other: Any ) = compare( other ) == 0

    def +( x: Int ) = toInt + x
    def +( x: Float ) = toDouble + x
    def +( x: Double ) = toDouble + x
    def +( x: String ) = toString + x
    def +( x: Long ): Long = value.asInstanceOf[Any] match {
        // If the internal value of the attribute is long we'll use it, otherwise
        // we'll call getInt.
        case l: java.lang.Long => l + x
        case l: Long => l + x
        case _ => toInt + x
    }

    def +=( x: Any ) = jattributeInstance.add( x )

    def -=( x: Int ) = jattributeInstance.add( -x )
    def -=( x: Long ) = jattributeInstance.add( -x )
    def -=( x: Float ) = jattributeInstance.add( -x )
    def -=( x: Double ) = jattributeInstance.add( -x )

    def compare(other: Any): Int = other match {
        // If 'other' is an AttributeInstance, get its value before calling compare.
        case attr: AttributeInstance => jattributeInstance.compare( attr.jattributeInstance )

        // If 'other' is a regex then see if the attribute value matches the regex.
        case regex: Regex =>
            if ( regex.findFirstIn( jattributeInstance.getString() ) == None )
                -1
            else
                0

        // Default case: call compare with the value.
        case _ => jattributeInstance.compare(other)
    }

    override def equals(other: Any) = compare( other ) == 0
    override def toString = jattributeInstance.getString()

    private def checkNull() = if ( isNull ) throw new ZeidonException( "Attribute is null" ).prependAttributeDef( attributeDef )

    def toBoolean: Boolean = if ( isNull ) false else jattributeInstance.getBoolean()
    def toInt: Int = { checkNull(); jattributeInstance.getInteger() }
    def toDouble: Double = if ( isNull ) Double.NaN else jattributeInstance.getDouble
}