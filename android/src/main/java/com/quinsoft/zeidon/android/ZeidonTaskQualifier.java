/**
 * 
 */
package com.quinsoft.zeidon.android;

import com.quinsoft.zeidon.TaskQualification;

/**
 * An android view that implements this interface can supply a Zeidon
 * TaskQualification object (either a task or view) that is used for
 * retrieving Zeidon views by name. 
 * 
 * @author dgc
 *
 */
public interface ZeidonTaskQualifier
{
    TaskQualification getTaskQualification();
}
