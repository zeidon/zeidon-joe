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

    Copyright 2009-2012 QuinSoft
 */

package com.quinsoft.zeidon.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.quinsoft.zeidon.EventData;
import com.quinsoft.zeidon.EventListener;
import com.quinsoft.zeidon.EventNotification;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.ZeidonLogger;

/**
 * Simple event listener that prints out a stack trace.
 * 
 * @author DG
 *
 */
public class EventStackTrace implements EventListener
{

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.EventListener#event(com.quinsoft.zeidon.EventNotification, com.quinsoft.zeidon.EventData)
     */
    @Override
    public void event( EventNotification notification, EventData data )
    {
        Task task = data.getTask();
        ZeidonLogger logger = task.log();
        
        logger.info( "Event notification: %s", notification.toString() );
        if ( data.getView() != null )
            logger.info( "    View = %s", data.getView() );
        if ( data.getViewEntity() != null )
            logger.info( "    ViewEntity = %s", data.getViewEntity() );
        if ( data.getViewAttribute() != null )
            logger.info( "    ViewAttribute = %s", data.getViewAttribute() );
        if ( data.getEntityInstance() != null )
            logger.info( "    EntityInstance = %s", data.getEntityInstance() );
        
        Exception e = new EventNotificationMessage();
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        e.printStackTrace( new PrintStream( bytes ) );
        logger.info( "Stack trace:\n%s", bytes.toString() );
    }
    
    private static class EventNotificationMessage extends Exception
    {
        private static final long serialVersionUID = 1L;
    }
}
