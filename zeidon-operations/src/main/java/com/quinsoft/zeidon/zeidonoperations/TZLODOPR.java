package com.quinsoft.zeidon.zeidonoperations;

import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.vml.VmlOperation;

/**
 * This file belongs to the c-to-java conversion system.  These files convert functionality
 * defined in older .c files into Java.  They use non-Java naming conventions to hopefully
 * make it easier to generate the correct .java files using the VML generator.
 *
 * This is the list of Zeidon global operations that are defined in tzlodopr.h.
 *
 * @author DG
 *
 */
public class TZLODOPR extends VmlOperation
{

    public TZLODOPR(TaskQualification taskQual)
    {
       super( taskQual );
    }

    public TZLODOPR(Object...objects)
    {
       this( findTaskQual( objects ) );
    }

    public int LoadZeidonPPE( View v, int type, View meta, String title, String text )
    {
       // TODO: Implement full conversion.
       return 0;
    }
}
