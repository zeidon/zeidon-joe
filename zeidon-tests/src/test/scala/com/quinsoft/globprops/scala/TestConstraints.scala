/**
 * This file is part of the Zeidon Java Object Engine (Zeidon JOE).
 *
 * Zeidon JOE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Zeidon JOE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Zeidon JOE.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2009-2014 QuinSoft
 */
package com.quinsoft.globprops.scala

import com.quinsoft.zeidon.scala.View
import com.quinsoft.zeidon.scala.ObjectOperations
import com.quinsoft.zeidon.EntityConstraintType
import com.quinsoft.zeidon.objectdefinition.EntityDef

/**
 * @author dgc
 *
 */
class TestConstraints( val view: View ) extends ObjectOperations {

    // For convenience we'll create a view variable with the same name as the LOD.
    val testConstraint = view

    def configurationSetConstraint(  entityDef: EntityDef, event: EntityConstraintType ) = {

        testConstraint.log.info( "Before: Constraint count = %s", view.ConfigurationSet.wConstraintCallCount )
        view.ConfigurationSet.wConstraintCallCount += 1L
        testConstraint.log.info( "Constraint count = %s", view.ConfigurationSet.wConstraintCallCount )

        event match {
            case EntityConstraintType.ACCEPT => {
                testConstraint.log.info( "ACCEPT constraint called with entity %s", entityDef.getName )
            }

            case EntityConstraintType.CREATE => {
                testConstraint.log.info( "CREATE constraint called with entity %s", entityDef.getName )
            }

            case EntityConstraintType.CANCEL => {
                testConstraint.log.info( "CANCEL constraint called with entity %s", entityDef.getName )
            }

            case EntityConstraintType.DELETE => {
                testConstraint.log.info( "DELETE constraint called with entity %s", entityDef.getName )
            }

            case EntityConstraintType.INCLUDE => {
                testConstraint.log.info( "INCLUDE constraint called with entity %s", entityDef.getName )
            }

            case EntityConstraintType.EXCLUDE => {
                testConstraint.log.info( "EXCLUDE constraint called with entity %s", entityDef.getName )
            }

            case _ => testConstraint.log.error( "Constraint %s called but we don't have any code!", event )
        }
    }
}