/**
 *
 */
package com.quinsoft.zeidon.scala

import com.quinsoft.zeidon.standardoe.ScalaHelper
import com.quinsoft.zeidon.ObjectConstraintType
import com.quinsoft.zeidon.EntityConstraintType
import com.quinsoft.zeidon.objectdefinition.EntityDef

/**
 * This class has a number of glue methods that help the JOE call Zeidon
 * operations written in scala.
 *
 * @author dgc
 *
 */
class ScalaHelperImpl extends ScalaHelper {

    var classLoader: ClassLoader = null

    def setClassLoader( classLoader: ClassLoader ): Unit = this.classLoader = classLoader

    /**
     * Call an object constraint on a view.
     */
    def executeObjectConstraint( jview: com.quinsoft.zeidon.View,
                                 constraintType: ObjectConstraintType ): Integer = {
        val jlodDef = jview.getLodDef
        val application = jlodDef.getApplication()
        val className = jlodDef.getSourceFileName()
        val operationsClass = classLoader.loadClass( className );
        val constructors = operationsClass.getConstructors()
        val constructor = constructors( 0 )
        val view = new View( jview ).basedOnLod( jlodDef.getName() )
        val instance = constructor.newInstance( view )
        val method = instance.getClass.getMethod( jlodDef.getConstraintOper(), constraintType.getClass() )
        val rc = method.invoke( instance, constraintType )
        return 0
    }

    /**
     * Call an entity constraint on a view.
     */
    def executeEntityConstraint( jview: com.quinsoft.zeidon.View,
                                 entityDef: EntityDef,
                                 constraintType: EntityConstraintType ): Integer = {
        val application = jview.getApplication()
        val className = entityDef.getSourceFileName()
        val operationsClass = classLoader.loadClass( className );
        val constructors = operationsClass.getConstructors()
        val constructor = constructors( 0 )
        val view = new View( jview ).basedOn( jview.getLodDef().getName() )
        val instance = constructor.newInstance( view )
        val method = instance.getClass.getMethod( entityDef.getConstraintOper(), entityDef.getClass(), constraintType.getClass() )
        val rc = method.invoke( instance, entityDef, constraintType )
        return 0
    }

    /**
     * This is called when the value for a derived attribute is requested.  The implementation
     * of this should set the value in the attributeInstance using setDerivedValue().
     *
     * @param attributeInstance
     * @return ignored
     */
    def calculateDerivedAttribute( attributeInstance: com.quinsoft.zeidon.AttributeInstance ): Integer = {
        val jview = attributeInstance.getView()
        val jlodDef = jview.getLodDef()
        val jattrdef = attributeInstance.getAttributeDef()
        val className = jattrdef.getDerivedOperationClassName()
        val operationsClass = classLoader.loadClass( className )
        val constructors = operationsClass.getConstructors()
        val constructor = constructors( 0 )
        val view = new View( jview )
        val instance = constructor.newInstance( view )
        val ai: AttributeInstance = new AttributeInstance( attributeInstance )
        val method = instance.getClass.getMethod( jattrdef.getDerivedOperationName(), ai.getClass() )
        val rc = method.invoke( instance, ai )
        return 0
    }

}