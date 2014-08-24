package com.quinsoft.zeidon;

import com.quinsoft.zeidon.objectdefinition.ViewEntity;

/**
 * Defines the interface for an activator object that is used to activate OIs.
 *
 * @author dgc
 *
 */
public interface Activator
{
    View init(Task task, View initialView, ActivateOptions options );

    /**
     * Activate the OI.
     *
     * @return
     */
    View activate();

    /**
     * Activate a subobject with subobjectRootEntity as the root.  If subobjectRootootEntity = ViewOD.root then
     * this activates the whole OI.
     *
     * @param subobjectRootEntity
     * @return
     */
    int activate( ViewEntity subobjectRootEntity );
}
