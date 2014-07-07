/**
 *
 */
package com.quinsoft.zeidon.dbhandler;

import java.lang.reflect.Constructor;

import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.AbstractOptionsConfiguration;
import com.quinsoft.zeidon.GenKeyHandler;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.ZeidonException;

/**
 * Some helper functions for loading and setting up JdbcHandlers.
 *
 * @author dgc
 *
 */
public class JdbcHandlerUtils
{
    private final AbstractOptionsConfiguration options;
    private final String                       dbName;
    private       String                       configGroupName;

    public JdbcHandlerUtils( AbstractOptionsConfiguration options, String dbName )
    {
        this.options = options;
        this.dbName = dbName;
    }

    private String getGroupName()
    {
        if ( ! StringUtils.isBlank( configGroupName ) )
            return configGroupName;

        String dbUrl = options.getOiSourceUrl();
        if ( ! dbUrl.startsWith( "jdbc:" ) )
            throw new ZeidonException( "Expecting jdbc url for oiSourceUrl: %s", dbUrl );

        String[] parts = dbUrl.split( ":" );
        if ( parts.length < 3 )
            throw new ZeidonException( "oiSourceUrl is not a valid JDBC URL.  Expecting 3 parts separated by ':' - %s", dbUrl );

        String dbType = parts[1];
        configGroupName = options.getApplication().getName() + "." + dbName + ".jdbc." + dbType;
        options.overrideConfigValue( "JdbcConfigGroupName", configGroupName );
        return configGroupName;
    }

    static private final Class<?>[] handlerConstructorArgs    = new Class<?>[] { Task.class, AbstractOptionsConfiguration.class };

    @SuppressWarnings("unchecked")
    public DbHandler getDbHandler()
    {
        DbHandler handler = options.getDbHandler();
        if ( handler != null )
            return handler;

        String handlerName = options.getConfigValue( getGroupName(), "DbHandler" );

        // If the handler name isn't specified we'll try to be smart and determine the default
        // handler using the connection string.
        if ( StringUtils.isBlank( handlerName ) )
        {
            String conn = options.getOiSourceUrl();
            if ( conn.startsWith( "jdbc:mysql:" ) )
                handlerName = MysqlJdbcHandler.class.getCanonicalName();
            else
            if ( conn.startsWith( "jdbc:sqlite:" ) )
                handlerName = SqliteJdbcHandler.class.getCanonicalName();
            else
                handlerName = JdbcHandler.class.getCanonicalName();
        }

        try
        {
            // For now we assume the DBHandler is the JDBC handler.
            ClassLoader loader = options.getTask().getObjectEngine().getClassLoader( handlerName );
            Class<? extends JdbcHandler> handlerClass;
            handlerClass = (Class<? extends JdbcHandler>) loader.loadClass( handlerName );
            Constructor<? extends JdbcHandler> constructor = handlerClass.getConstructor( handlerConstructorArgs );
            JdbcHandler jdbcHandler = constructor.newInstance( options.getTask(), options );
            return jdbcHandler;
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e ).appendMessage( "Handler name = ", handlerName );
        }
    }

    public GenKeyHandler getGenKeyHandler()
    {
        // For now we assume the dbhandler is also the genkey handler.
        return (JdbcHandler) getDbHandler();
    }
}
