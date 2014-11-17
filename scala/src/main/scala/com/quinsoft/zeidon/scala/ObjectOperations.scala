/**
 *
 */
package com.quinsoft.zeidon.scala

import java.lang.reflect.Constructor
import java.lang.reflect.Method
import com.quinsoft.zeidon.ZeidonException
import com.quinsoft.zeidon.objectdefinition.LodDef
import scala.collection.concurrent.TrieMap
import scala.collection.mutable.WrappedArray
import org.apache.commons.lang3.StringUtils

/**
 * A trait to give Scala objects access to VML-like syntax.  This is similar to
 * ZeidonOperations except it requires a View be supplied instead of a task.  It is
 * intended to be use by classes that define Object Operations that will be executed
 * dynamically.
 */
trait ObjectOperations extends ZeidonOperations {
    val view: View
    val task: Task = view.task
}

/**
 * Keeps track of information needed for an object operation.
 *
 * @author dgc
 *
 */
private[scala] class ObjectOperationCaller( private[scala] val operationName: String,
                                            private[scala] val className: String,
                                            args: AnyRef* ) {

    val clazz = Class.forName( className )
    if ( clazz == null )
        throw new ZeidonException( "Couldn't load class '%s'", className )

    val constructor = {
        val constructors = clazz.getConstructors()
        if ( constructors.length != 1 )
            throw new ZeidonException( "Unexpected number of constructors for %s", className )

        constructors(0)
    }

    val argLength = args.length

    val method = {
        val matchedMethods = for ( m <- clazz.getMethods if m.getName().equals(operationName) ) yield m
        if ( matchedMethods.length == 0 )
            throw new ZeidonException( "Method '%s' not found in '%s'", operationName, className )

        if ( matchedMethods.length > 1 )
            throw new ZeidonException( "Found multiple methods '%s' not found in '%s'", operationName, className )

        matchedMethods(0)
    }

    if ( method.getParameterTypes().length != args.length )
        throw new ZeidonException( "Unexpected number of arguments for method.  Expected %d, got %d",
                                   Int.box( method.getParameterTypes().length ), Int.box( argLength ) )

    def invokeOperation( view: View, args: AnyRef*): AnyRef = {
        if ( args.length != argLength )
            throw new ZeidonException( "Unexpected number of arguments.  Expected %d, got %d",
                                       Int.box( argLength ), Int.box( args.length ) )

        val instance = constructor.newInstance( view )
        view.task.log.debug( "Invoking %s.%s", className, operationName )
        method.invoke(instance, args:_*)
    }
}

/**
 * This class is used to keep track of object operations by name.
 */
private [scala] class ObjectOperationMap() {

    private val map = new TrieMap[String, ObjectOperationCaller]()

    def getObjectOperation( operName: String, jlodDef: LodDef, args: AnyRef* ) = {
        var className = jlodDef.getSourceFileName()
        if ( StringUtils.isBlank( className ) )
            className = jlodDef.getApplication().getPackage() + "." + jlodDef.getLibraryName()

        val key = className + "." + operName
        if ( ! map.contains( key ) )
            map += (key -> new ObjectOperationCaller( operName, className, args:_* ) )

        map(key)
    }
}