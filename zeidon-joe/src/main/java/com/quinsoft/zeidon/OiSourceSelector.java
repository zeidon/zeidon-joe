/**
 *
 */
package com.quinsoft.zeidon;

import java.util.List;


/**
 * Determines the Activator and Committer that will be used to activate/commit
 * an OI.
 *
 * @author dgc
 *
 */
public interface OiSourceSelector
{
    Activator getActivator( Task task, Application application, ActivateOptions options );
    Committer getCommitter( Task task, List<? extends View> viewList, CommitOptions options );
}
