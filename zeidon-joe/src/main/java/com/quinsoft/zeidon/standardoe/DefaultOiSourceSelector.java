package com.quinsoft.zeidon.standardoe;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.ActivateOptions;
import com.quinsoft.zeidon.Activator;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.CommitOptions;
import com.quinsoft.zeidon.Committer;
import com.quinsoft.zeidon.OiSourceSelector;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;

/**
 * @author dgc
 *
 */
public class DefaultOiSourceSelector implements OiSourceSelector
{
    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.OiServerSelector#getActivator(com.quinsoft.zeidon.Task, com.quinsoft.zeidon.View)
     */
    @Override
    public Activator getActivator( Task task, Application application, ActivateOptions options )
    {
        Activator activator = options.getActivator();
        if ( activator != null )
            return activator;

        String url = options.getOiSourceUrl();
        if ( StringUtils.isBlank( url ) )
            throw new ZeidonException( "oiSourceUrl has not been specified in config" )
                                .appendMessage( "Configuration source: %s", options.getConfigSource() )
                                .appendMessage( "Application: %s", options.getApplication().getName() );

        if ( url.startsWith( "jdbc:" ) )
            return new ActivateOiFromDB();

        if ( url.startsWith( "http:" ) || url.startsWith( "https:" ) )
            return new ActivateOiFromRestServer( url );

        if ( url.startsWith( "file:" ) || url.startsWith( "resource:" ) )
            return new ActivateOiFromFileDb();

        throw new ZeidonException( "oiSourceUrl specifies unknown protocol: %s", url );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.OiServerSelector#getCommitter(com.quinsoft.zeidon.Task, java.util.List)
     */
    @Override
    public Committer getCommitter( Task task, List<? extends View> viewList, CommitOptions options )
    {
        String url = options.getOiSourceUrl();
        if ( StringUtils.isBlank( url ) )
            throw new ZeidonException( "oiSourceUrl has not been specified in config" )
                            .appendMessage( "Configuration source: %s", options.getConfigSource() )
                            .appendMessage( "Application: %s", options.getApplication().getName() );

        if ( url.startsWith( "jdbc:" ) )
        {
            View v = viewList.get( 0 );

            // Is there a genkey handler in the ViewOd?  If not, then use
            // the committer that will let the DB generate the keys.
            if ( StringUtils.isBlank( v.getViewOd().getGenkeyHandler() ) )
                return new CommitToSqlWithDbGeneratedKeys();
            else
                return new CommitToDbUsingGenkeyHandler();
        }

        if ( url.startsWith( "http:" ) || url.startsWith( "https:" ) )
            return new CommitToRestServer();

        if ( url.startsWith( "file:" ) || url.startsWith( "resource:" ) )
            return new CommitOiToFileDb();

        throw new ZeidonException( "oiSourceUrl specifies unknown protocol: %s", url );
    }
}
