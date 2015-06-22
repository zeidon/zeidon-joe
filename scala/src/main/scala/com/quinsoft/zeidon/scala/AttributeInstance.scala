/**
 *
 */
package com.quinsoft.zeidon.scala

import scala.util.matching.Regex
import com.quinsoft.zeidon.ZeidonException

/**
 * Object that represents an attribute value in an entity instance.
 */
class AttributeInstance( val jattributeInstance: com.quinsoft.zeidon.AttributeInstance ) {

    /**
     * Returns true if the attribute is null.
     */
    def isNull = jattributeInstance.isNull()

    /**
     * Returns true if the attribute is null or the string representation of the
     * attribute value is the empty string ("").
     */
    def isEmpty = jattributeInstance.isEmpty

    /**
     * Returns true if the attribute is null, the string representation of the
     * attribute value is the empty string (""), or the string is all whitespace.
     */
    def isBlank = jattributeInstance.isBlank

    /**
     * Returns true if this attribute has been updated since being read from the DB.
     */
    def isUpdated = jattributeInstance.isUpdated()

    /**
     * Returns true if this value is read-only (i.e. can not be changed).
     */
    def isReadOnly =  ! jattributeInstance.getAttributeDef().isUpdate()

    /**
     * Sets the value of this attribute.
     *
     * Domain processing will be used to potentially convert the value from the
     * source object to the internal value.
     */
    def setValue( any: Any, contextName: String = null ) = jattributeInstance.setValue( any, contextName )

    /**
     * Sets the value of a derived attribute.
     *
     * This does not set the Updated flag for the containing entity instance.
     * This is intended to be used from inside derived domain code.
     */
    def setDerivedValue( any: Any ) = jattributeInstance.setDerivedValue( any )

    /**
     * Returns the internal value of the attribute.
     */
    def value = jattributeInstance.getValue()

    /**
     * Returns the name of this attribute.
     */
    def name = jattributeInstance .getAttributeDef().getName()

    /**
     * Returns the AttributeDef for this attribute.
     */
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

    def <( other: Any ) = compare( other ) < 0
    def >( other: Any ) = compare( other ) > 0
    def >=( other: Any ) = compare( other ) >= 0
    def <=( other: Any ) = compare( other ) <= 0
    def !=( other: AttributeInstance ) = compare( other ) != 0

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

    /**
     * Returns true if attribute is between values in tuple, exclusively.
     */
    def >< (values: Tuple2[Any,Any]) = (this > values._1) && (this < values._2)
    def >=< (values: Tuple2[Any,Any]) = (this >= values._1) && (this < values._2)
    def ><= (values: Tuple2[Any,Any]) = (this > values._1) && (this <= values._2)
    def >=<= (values: Tuple2[Any,Any]) = (this >= values._1) && (this <= values._2)

    def compare(other: Any): Int = other match {
        // If 'other' is a Scala AttributeInstance, get its value before calling compare.
        // We have to do this here because the JOE doesn't know anything about Scala objects.
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

    /**
     * Returns the attribute value as a string.  If the context is not specified then
     * the default context will be used.
     *
     * toString() is the preferred method for getting a string value.  This is included
     * for compatibility with the Java way of getting strings.
     */
    def getString( contextName : String = "" ) = jattributeInstance.getString( contextName )

    /**
     * Returns the attribute value as a string.  If the context is not specified then
     * the default context will be used.
     *
     * toString() is the preferred method for getting a string value.  This is included
     * for compatibility with the Java way of doing things.
     */
    def toString( contextName : String = "" ) = jattributeInstance.getString( contextName )

    /**
     * Returns the value of the attribute as a string using the default context.
     */
    override def toString = jattributeInstance.getString( "" )

    private def checkNull() = if ( isNull ) throw new ZeidonException( "Attribute is null" ).prependAttributeDef( attributeDef )

    /**
     * Returns true if this attribute is "truthy"
     *
     * A truthy attribute is an attribute that is not null and the internal value can
     * be intepreted as "true".  E.g. an integer is non-0.  The advantage of this over
     * toBoolean is that it can handle null attributes.
     */
    def isTruthy: Boolean = { ! isNull && toBoolean }

    /**
     * Converts the attribute value to a boolean using default domain processing.
     */
    def toBoolean: Boolean = { jattributeInstance.getBoolean() }

    /**
     * Converts the attribute value to an integer using default domain processing.
     */
    def toInt: Int = { jattributeInstance.getInteger() }

    /**
     * Converts the attribute value to a double using default domain processing.
     */
    def toDouble: Double = { jattributeInstance.getDouble }
    
    /**
     * Converts the attribute value to a double using domain processing with 
     * the specified context.
     */
    def toDouble( contextName: String ): Double = { jattributeInstance.getDouble( contextName ) }
    
    override def hashCode() = jattributeInstance.hashCode()
    override def equals( obj: Any ) = compare( obj ) == 0 //jattributeInstance.equals( obj ) 
}

object AttributeInstance {
    implicit def attributeInstance2String( attr: AttributeInstance ) = attr.jattributeInstance.getString
    implicit def attributeInstance2Integer( attr: AttributeInstance ): Integer = attr.toInt
    implicit def attributeInstance2Int( attr: AttributeInstance ): Int = attr.toInt
    implicit def attributeInstance2Double( attr: AttributeInstance ): Double = attr.toDouble
    implicit def attributeInstance2Boolean( attr: AttributeInstance ): Boolean = attr.toBoolean
    implicit def attributeInstance2JAI( attr: AttributeInstance ) = attr.jattributeInstance
}