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

import com.quinsoft.zeidon.ZeidonException

/**
 * A wrapper class around the AnyRef returned by an Zeidon Object Operation.
 * This will automatically convert the value to standard Scala types.
 *
 */
case class ObjectOperationResult( val value: AnyRef ) {

}

object ObjectOperationResult {
    implicit def result2Cursor( result: ObjectOperationResult ) = result.value.asInstanceOf[ EntityCursor ]
    implicit def result2List[T]( result: ObjectOperationResult ) = result.value.asInstanceOf[ List[T] ]

    implicit def result2String( result: ObjectOperationResult) = result.value match {
        case null => null
        case a: com.quinsoft.zeidon.AttributeInstance => a.getString()
        case _ => result.value.toString()
    }

    implicit def result2Int( result: ObjectOperationResult ): Int = result.value match {
        case null => throw new ZeidonException( "Can't convert null to Int" )
        case a: AttributeInstance => a.toInt
        case a: com.quinsoft.zeidon.AttributeInstance => a.getInteger()
        case n: Number => n.intValue()
        case _ => result.value.asInstanceOf[ Int ]
    }

    implicit def result2v( result: ObjectOperationResult ) : View = result.value match {
        case null => null
        case v: View => v
        case v: com.quinsoft.zeidon.View => new View( v )
        case _ => throw new ZeidonException( "Can't convert class %s to AttributeInstance", result.value.getClass().getName() )
    }

    implicit def result2Ai( result: ObjectOperationResult ) : AttributeInstance = result.value match {
        case null => null
        case a: AttributeInstance => a
        case a: com.quinsoft.zeidon.AttributeInstance => new AttributeInstance( a )
        case _ => throw new ZeidonException( "Can't convert class %s to AttributeInstance", result.value.getClass().getName() )
    }

    implicit def result2Ei( result: ObjectOperationResult ): EntityInstance = result.value match {
        case null => null
        case ei: EntityInstance => ei
        case cursor: com.quinsoft.zeidon.EntityCursor => new EntityInstance( cursor.getEntityInstance() )
        case ei: com.quinsoft.zeidon.EntityInstance => new EntityInstance( ei )
        case cursor: EntityCursor => new EntityInstance( cursor.getEntityInstance )
        case _ => throw new ZeidonException( "Can't convert class %s to EntityInstance", result.value.getClass().getName() )
    }

    implicit def result2Ec( result: ObjectOperationResult ): EntityCursor = result.value match {
        case null => null
        case cursor: EntityCursor => cursor
        case _ => throw new ZeidonException( "Can't convert class %s to EntityInstance", result.value.getClass().getName() )
    }
//    implicit def result2T[ T : AnyRef ]( result: ObjectOperationResult ): T = {
//        result.value.asInstanceOf[ T ]
//    }

}