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
 * Copyright 2009-2015 QuinSoft
 */
package com.quinsoft.zeidon.scala

import scala.util.control.ControlThrowable

/**
 * This class is the equivalent of Scala's Breaks class.  It is used to give Zeidon
 * loop constructs the ability to execute "next()" to move to the next entity in a
 * cursor loop.  This is largely cribbed from Breaks.scala.
 *
 * @author dgc
 *
 */
class Nexts {
    private val nextException = new NextControl

    /**
     * A block from which one can process the next entity in a cursor by
     * using next().
     */
    def nextable( op: => Unit ) {
        try {
            op
        } catch {
            case ex: NextControl =>
                if ( ex ne nextException ) throw ex
        }
    }

    def next(): Nothing = { throw nextException }
}

object Nexts extends Nexts

private class NextControl extends ControlThrowable