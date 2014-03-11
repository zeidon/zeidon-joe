/**
 *
 */
package com.quinsoft.zeidon.scala

import com.quinsoft.zeidon.standardoe.ScalaHelper
import com.quinsoft.zeidon.ObjectConstraintType

/**
 * This class has a number of glue methods that help the JOE call Zeidon
 * operations written in scala.
 *
 * @author dgc
 *
 */
class ScalaHelperImpl extends ScalaHelper {

    /**
     * Call an object constraint on a view.
     */
    def executeObjectConstraint( jview: com.quinsoft.zeidon.View,
                                 constraintType: ObjectConstraintType,
                                 loader: ClassLoader ): Integer = {
      val jviewOd = jview.getViewOd()
      val application = jviewOd.getApplication()
      val className = application.getPackage() + "." + jviewOd.getName()
      val operationsClass = loader.loadClass( className );
      val constructors = operationsClass.getConstructors()
      val constructor = constructors(0)
      val view = new View( jview ).basedOnLod( jviewOd.getName() )
      val instance = constructor.newInstance( view.task )
      val method = instance.getClass.getMethod(jviewOd.getConstraintOper(), view.getClass(), constraintType.getClass() )
      val rc = method.invoke(instance, view, constraintType)
      return 0
    }
}