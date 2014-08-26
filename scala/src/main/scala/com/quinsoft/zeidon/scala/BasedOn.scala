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
package com.quinsoft.zeidon.scala

import scala.reflect.macros.Context
import scala.language.experimental.macros
import scala.annotation.StaticAnnotation

/**
 * Annotation for setting LOD for method arguments.
 *
 * @author dgc
 *
 */
case class basedOn( lodName: String,
                    appName: String = "",
                    notEmpty: Boolean = true ) extends scala.annotation.StaticAnnotation {

    def this( viewDef: ViewDef ) = this( viewDef.lodName , viewDef.applicationName )
    def macroTransform(annottees: Any*) = macro basedOnMacro.impl
}

object basedOnMacro {
    def impl( c: Context )( annottees: c.Expr[ Any ]* ): c.Expr[ Any ] = {
        import c.universe._
        val inputs = annottees.map( _.tree ).toList
        val ( annottee, expandees ) = inputs match {
            case ( param: ValDef ) :: ( rest @ ( _ :: _ ) ) => ( param, rest )
            case ( param: TypeDef ) :: ( rest @ ( _ :: _ ) ) => ( param, rest )
            case _ => ( EmptyTree, inputs )
        }
        println( ( annottee, expandees ) )
        val outputs = expandees
        c.Expr[ Any ]( Block( outputs, Literal( Constant( () ) ) ) )
    }
}