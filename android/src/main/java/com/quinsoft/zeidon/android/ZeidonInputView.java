/**
 * 
 */
package com.quinsoft.zeidon.android;

/**
 * Views that implement this interface are used to set Zeidon attribute values.
 * 
 * @author dgc
 *
 */
public interface ZeidonInputView
{
    /**
     * Copies the value from the android view to an OI.
     */
    void copyValuesToOi();
    
    String getViewName();
    String getEntityName();
}
