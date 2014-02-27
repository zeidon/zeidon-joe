/**
 *
 */
package com.quinsoft.zeidon.standardoe;

import com.quinsoft.zeidon.ObjectConstraintType;
import com.quinsoft.zeidon.View;

/**
 * This is used to call Scala operations (like constraint operations) from JOE.
 * The Zeidon Scala wrapper implements this interface for calling operations.
 *
 * @author dgc
 *
 */
public interface ScalaHelper
{
    Integer executeObjectConstraint( View view, ObjectConstraintType constraintType, ClassLoader loader );
}
