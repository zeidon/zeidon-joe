/**
 *
 */
package com.quinsoft.zeidon;

import java.util.List;

/**
 * Interface that defines methods used to commit an object to a DB.
 *
 * @author dgc
 *
 */
public interface Committer
{
    void init( Task task, List<? extends View> viewList, CommitOptions options );

    List<? extends View> commit();
}
