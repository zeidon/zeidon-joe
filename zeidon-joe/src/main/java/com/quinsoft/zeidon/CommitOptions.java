/**
 *
 */
package com.quinsoft.zeidon;

import java.util.Collection;
import java.util.EnumSet;
import java.util.List;

/**
 * Optional options for commit.
 *
 * @author dgc
 *
 */
public class CommitOptions extends AbstractOptionsConfiguration
{
    private EnumSet<CommitFlags> control = CommitFlags.NONE;
    private List<View>           viewList;

    public CommitOptions( TaskQualification task )
    {
        super( task.getTask() );
    }

    @Override
    public Application getApplication()
    {
        return viewList.get( 0 ).getViewOd().getApplication();
    }

    public EnumSet<CommitFlags> getControl()
    {
        return control;
    }

    public CommitOptions setControl( EnumSet<CommitFlags> control )
    {
        this.control = control;
        return this;
    }

    @Override
    public CommitOptions overrideConfigValue( String key, String value )
    {
        super.overrideConfigValue( key, value );
        return this;
    }

    public Collection<View> getViewList()
    {
        return viewList;
    }

    public void setViewList( List<View> viewList )
    {
        this.viewList = viewList;
    }
}
