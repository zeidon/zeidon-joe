/**
 * 
 */
package com.quinsoft.zeidon.android;

import com.quinsoft.zeidon.View;

/**
 * A Zeidon android views that displays data is an output view and must
 * implement this interface.
 * 
 * @author dgc
 *
 */
public interface ZeidonDisplayView
{
    /**
     * This is called from a parent ZeidonDisplayView to set data for the android view from
     * data in an OI.  If the view has child views then it is assumed that this call will
     * also set the values for them..
     */
    void setFromOi();
    
    /**
     * Gets the Zeidon view assigned to map this android View.  If this returns null then
     * the mapping view is assumed to be the same as the named view.
     * 
     * @return
     */
    View getMappingView();
    String getViewName();
    String getEntityName();
    
    void setZeidonParent( android.view.View parent );
    android.view.View getZeidonParent();
}
