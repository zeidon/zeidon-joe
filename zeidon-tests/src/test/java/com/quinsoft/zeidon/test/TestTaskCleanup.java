/**
 *
 */
package com.quinsoft.zeidon.test;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalCause;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.config.HomeDirectoryFromString;
import com.quinsoft.zeidon.standardoe.DefaultJavaOeConfiguration;
import com.quinsoft.zeidon.standardoe.JavaObjectEngine;

/**
 * @author dgc
 *
 */
public class TestTaskCleanup
{
    private final Task         zeidonSystem;
    private final ObjectEngine oe;

    public TestTaskCleanup()
    {
        // Set up options to run OE with cleanup.
        DefaultJavaOeConfiguration options = new DefaultJavaOeConfiguration();
        options.setHomeDirectory( new HomeDirectoryFromString( "src/test/resources/testdata", options.getZeidonLogger() ) );

        Cache<String, Task> c = CacheBuilder.newBuilder()
                                            .concurrencyLevel( 2 )
                                            .expireAfterAccess( 10, TimeUnit.SECONDS )
                                            .removalListener( new TaskCacheRemovalListener() )
                                            .build();
        ConcurrentMap<String, Task> taskCacheMap = c.asMap();
        options.setTaskCacheMap( taskCacheMap );

        oe = JavaObjectEngine.getInstance( options );
        zeidonSystem = oe.getSystemTask();
    }

    public void test() throws InterruptedException
    {
        Task zencas = oe.createTask( "ZENCAs" );
        String id = zencas.getTaskId();
        zencas = null;

        Thread.sleep( 15000 );

        zencas = oe.createTask( "ZENCAs" );
        zencas = oe.getTaskById( "abc" );
        zencas = oe.getTaskById( id );
        if ( zencas != null )
            throw new ZeidonException( "Task wasn't cleaned up" );
    }

    /**
     * Logs a warning if a task is ejected because it timed out.
     *
     * @author dgc
     *
     */
    private class TaskCacheRemovalListener implements RemovalListener<String, Task>
    {
        @Override
        public void onRemoval( RemovalNotification<String, Task> notification )
        {
            if ( notification.getCause() == RemovalCause.EXPIRED )
            {
                notification.getValue().log().warn( "This task was ejected from task cache map" );
                notification.getValue().dropTask();
            }
        }
    }

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main( String[] args ) throws InterruptedException
    {
        String properties = System.getProperties().toString();
        System.out.println( "CWD = " + System.getProperty("user.dir") );
        System.out.println( "CWD = " + System.getProperty("user.DIR") );
        System.out.println( "ZEIDON_HOME = " + System.getenv("ZEIDON_HOME") );

        TestTaskCleanup test = new TestTaskCleanup();
        test.test();
    }
}
