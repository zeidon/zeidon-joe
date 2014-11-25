/**
    This file is part of the Zeidon Java Object Engine (Zeidon JOE).

    Zeidon JOE is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Zeidon JOE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with Zeidon JOE.  If not, see <http://www.gnu.org/licenses/>.

    Copyright 2009-2014 QuinSoft
 */
package com.quinsoft.zeidon.standardoe;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.CommitOptions;
import com.quinsoft.zeidon.Committer;
import com.quinsoft.zeidon.DeserializeOi;
import com.quinsoft.zeidon.SerializeOi;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.ZeidonRestException;

/**
 * @author dgc
 *
 */
public class CommitToRestServer implements Committer
{
    /**
     * The WriteOptions for creating the JSON stream.
     */
    private final static SerializeOi JSON_WRITE_OPTIONS = new SerializeOi().withIncremental();

    private Set<ViewImpl>  viewList;
    private Task           task;
    private CommitOptions  options;
    private Application    application;
    private List<? extends View> originalList;

    /**
     * Set of the OIs in the original views.
     */
    private Set<ObjectInstance> originalOiSet;

    /**
     * URL that specifies the server.  E.g. "http://localhost:8080"
     */
    private String serverUrl;

    /**
     * The fully qualified url, including the path.
     * E.g. "http://localhost:8080/activate/APP-NAME".
     */
    private String url;

    /**
     * Map that lets logic find and EI by tags.  The tag is the EntityKey of the original
     * EIs (before commit) and the EI is from the new OIs.
     */
    private Map<String, EntityInstanceImpl> tagMap;

    /**
     * True if there are EIs in the list of views that are linked to EI's that
     * belong to OIs outside of this commit.  This will have some impact on how
     * we merge the results from the remote server.
     */
    private boolean hasExternallyLinkedInstances = false;

    @Override
    public void init( Task task, List<? extends View> list, CommitOptions options )
    {
        this.task = task;
        this.viewList = new HashSet<ViewImpl>();
        this.options = options;

        originalList = list;
        originalOiSet = new HashSet<ObjectInstance>();
        for ( View v : list )
        {
            if ( application == null )
                v.getApplication();

            ViewImpl newView = ((InternalView) v).getViewImpl();
            ObjectInstance oi = newView.getObjectInstance();
            if ( ! originalOiSet.contains( oi ) )
            {
                originalOiSet.add( oi );
                viewList.add( newView );
            }
        }

        serverUrl = options.getOiSourceUrl();
        url = String.format( "%s/commit?application=%s", serverUrl, application.getName() );
    }

    @Override
    public List<? extends View> commit()
    {
        try
        {
            copyEntityKeysToTags();

            String json = task.serializeOi().asJson().withIncremental().addViews( viewList ).toStringWriter().toString();
            List<View> views = makePostCall( json );
            View restRc = views.get( 0 );
            views.remove( 0 ); // Remove the RC from the list of views.

            supersedeEis( views );
            return originalList;
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e );
        }
    }

    /**
     * This takes the views returned from the server and updates the original EIs to point
     * to the new EIs.
     *
     * @param newViews Views returned from the server.
     */
    private void supersedeEis( List<View> newViews )
    {
        // Create a map of the tags that points to the new EIs.
        createTagMap( newViews );

        // Loop through all the EIs in the original OIs and set them to be superseded
        // by the new EIs.
        for ( ObjectInstance origOi: originalOiSet )
        {
            ObjectInstance newOi = null;

            for ( EntityInstanceImpl origEi = origOi.getRootEntityInstance();
                  origEi != null;
                  origEi = origEi.getNextHier() )
            {
                String tag = origEi.getTag();
                assert ! StringUtils.isBlank( tag ) : "Tag is blank!";

                EntityInstanceImpl newEi = tagMap.get( tag );
                if ( newEi == null )
                {
                    // If we get here then the original EI should have been deleted.  We can't
                    // verify that because it's possible that it was deleted by the commit constraint
                    // which is only run on the server.  We'll set the prev/next pointers to isolate
                    // this EI.
                }
                else
                {
                    newOi = newEi.getObjectInstance();

                    // Update any linked instances to be linked with the new EI.
                    for ( EntityInstanceImpl linked: origEi.getLinkedInstances() )
                    {
                        // If the linked EI is part of the current commit then skip it
                        // because it will be handled as part of the main loop.
                        if ( originalOiSet.contains( linked.getObjectInstance() ) )
                            continue;

                        // Reset the linked instance to be linked with newEi.
                        linked.linkInstances( newEi );
                        linked.setCreated( false );
                        linked.setUpdated( false );
                    }

                    // Set the next version.  This will effectively update any cursors pointing at
                    // origEi.
                    origEi.setNextVersion( newEi );
                }

                // It's possible a cursor is pointing to this EI.  Set prev/next pointers to
                // null so everything else can be GC'd.
                origEi.setEiPointersToNull();

            } // for each EI...

            if ( newOi == null )
            {
                // If we get here then there are *no* EI's from the origOi that are in the new
                // OIs.  This can happen if all the EI's were deleted.  Reset origOi to indicate
                // that it's empty.
                origOi.setRootEntityInstance( null );
            }
            else
            {
                // TODO: copy OI settings, set root EI.
                origOi.setRootEntityInstance( newOi.getRootEntityInstance() );
            }

            origOi.setUpdated( false );
            origOi.setUpdatedFile( false );

        } // for each OI...
    }

    private void createTagMap(List<View> views)
    {
        tagMap = new HashMap<String, EntityInstanceImpl>();
        for ( View v : views )
        {
            ObjectInstance oi = ((ViewImpl) v).getObjectInstance();
            for ( EntityInstanceImpl ei = oi.getRootEntityInstance();
                  ei != null;
                  ei = ei.getNextHier() )
            {
                tagMap.put( ei.getTag(), ei );
            }
        }
    }

    private List<View> makePostCall( String json )
    {
        String stringResponse = null;

        try
        {
            assert serverUrl.startsWith( "http://" ) || serverUrl.startsWith( "https://" ) : "Unexpected oiSourceUrl " + serverUrl;

            getTask().log().debug( "Committing to REST URL: %s", url );
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost( url );

            StringEntity entity = new StringEntity( json );
            post.setEntity( entity );
            post.setHeader( "Content-Type", "application/json" );

            HttpResponse response = client.execute( post );
            InputStream stream = response.getEntity().getContent();
            StatusLine status = response.getStatusLine();
            task.log().info( "Status from http activate = %s", status );
            int statusCode = status.getStatusCode();

            // If we're in debug mode, print out the results.
            if ( task.log().isDebugEnabled() || statusCode != 200 )
            {
                StringWriter writer = new StringWriter();
                IOUtils.copy(stream, writer, "UTF-8");
                stringResponse = writer.toString();
                task.log().debug( "REST response: %s", stringResponse );
                stream = IOUtils.toInputStream(stringResponse, "UTF-8");
            }

            if ( statusCode != 200 )
                throw new ZeidonException( "http activate failed with status %s", status );

            List<View> views = new DeserializeOi( getTask() )
                                        .asJson()
                                        .fromInputStream( stream )
                                        .activate();
            View restRc = views.get( 0 );
            restRc.logObjectInstance();
            Integer rc = restRc.cursor( "RestResponse" ).getAttribute( "ReturnCode" ).getInteger();
            if ( rc != 0 )
            {
                String errorMsg = restRc.cursor( "RestResponse" ).getAttribute( "ErrorMessage" ).getString();
                throw new ZeidonRestException( "Error activating OI from REST server %d", rc )
                                                .appendMessage( "%s", errorMsg );
            }

            return views;
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e ).appendMessage( "web URL = %s", url );

        }

    }
    /**
     * Copy the EntityKeys to the tag.  We'll use these to merge the OIs returned from the server.
     * Also sets hasExternallyLinkedInstances.
     */
    private void copyEntityKeysToTags()
    {
        for ( ObjectInstance oi: originalOiSet )
        {
            for ( EntityInstanceImpl ei = oi.getRootEntityInstance();
                  ei != null;
                  ei = ei.getNextHier() )
            {
                ei.setTag( Long.toString( ei.getEntityKey() ) );

                // Check to see if any linked instances are outside of the OI set.
                if ( ! hasExternallyLinkedInstances ) // Have we already determined there are external EIs?
                {
                    // No.  Loop through linked instances to see if they belong to an OI that
                    // is not part of this commit.
                    for ( EntityInstanceImpl linked : ei.getLinkedInstances() )
                    {
                        if ( ! originalOiSet.contains( linked.getObjectInstance() ) )
                        {
                            hasExternallyLinkedInstances = true;
                            break; // We don't need to search any more.
                        }
                    }
                }
            }
        }
    }

    private Task getTask()
    {
        return task;
    }
}
