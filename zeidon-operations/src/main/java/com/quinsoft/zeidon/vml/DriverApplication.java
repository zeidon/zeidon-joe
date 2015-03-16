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

    Copyright 2009-2015 QuinSoft
 */
package com.quinsoft.zeidon.vml;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.standardoe.JavaObjectEngine;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *@author DKS
 */
public class DriverApplication
{
   protected static final int zLEVEL_SUBTASK     = 1;
   protected static final int zLEVEL_TASK        = 2;
   protected static final int zLEVEL_APPLICATION = 4;
   protected static final int zLEVEL_SYSTEM      = 8;
   protected static final int zLEVEL_ALL         = 255;
   protected static final int zLEVEL_ANY         = 255;

   /**
    * Contains information regarding dialogs/tasks for an application.
    */
   private static class SubtaskList
   {
      private AtomicInteger dialogCount = new AtomicInteger( 0 );
      private ConcurrentHashMap<Integer, SubtaskItem> subtaskMap = new ConcurrentHashMap<Integer, SubtaskItem>();
   }

   private static class SubtaskItem
   {
      private Task   task;
      private View   view;
      private String dialogTag;
      private String windowTag;
      private String command;

      private SubtaskItem( View view, String dialogTag, String windowTag, String command )
      {
         this.task = view.getTask();
         this.view = view;
         this.dialogTag = dialogTag;
         this.windowTag = windowTag;
         this.command = command;
      }

      private SubtaskItem( View view )
      {
         this.task = view.getTask();
         this.view = view;
         this.dialogTag = "";
         this.windowTag = "";
         this.command = "";
      }
   }

   public static Task GetTaskFromSubtask( TaskQualification taskView, int subtask )
   {
      SubtaskList subtaskList = (SubtaskList) taskView.getTask( ).getCacheMap( SubtaskList.class );
      return subtaskList.subtaskMap.get( subtask ).task;
   }

   public static View GetViewFromSubtask( TaskQualification taskView, int subtask )
   {
      SubtaskList subtaskList = (SubtaskList) taskView.getTask( ).getCacheMap( SubtaskList.class );
      return subtaskList.subtaskMap.get( subtask ).view;
   }

   public static String GetDialogTagFromSubtask( TaskQualification taskView, int subtask )
   {
      SubtaskList subtaskList = (SubtaskList) taskView.getTask( ).getCacheMap( SubtaskList.class );
      return subtaskList.subtaskMap.get( subtask ).dialogTag;
   }

   public static String GetWindowTagFromSubtask( TaskQualification taskView, int subtask )
   {
      SubtaskList subtaskList = (SubtaskList) taskView.getTask( ).getCacheMap( SubtaskList.class );
      return subtaskList.subtaskMap.get( subtask ).windowTag;
   }

   public static String GetCommandFromSubtask( TaskQualification taskView, int subtask )
   {
      SubtaskList subtaskList = (SubtaskList) taskView.getTask( ).getCacheMap( SubtaskList.class );
      return subtaskList.subtaskMap.get( subtask ).command;
   }

   public static int TraceLocalS( String label, String text )
   {
      System.out.println( label + " " + text );
      return 0;
   }

   public static int TraceLocalI( String label, int i )
   {
      return TraceLocalS( label, Integer.toString( i ) );
   }

   public static int TraceLocal( String strFormat, Object... arr )
   {
      String tgtString = String.format( strFormat, arr );
      return TraceLocalS( tgtString, "" );
   }

   public static int CreateSubtaskForDialogWindow( View vSubtask, String dialogTag, String windowTag, String command )
   {
      // Generated VML uses ints as file handles so we need to convert a File to a int.
      // We'll increment a file count and store the File in a concurrent hashmap.
      View v = VmlOperation.getView( vSubtask );
      Task t = v.getTask( );
      SubtaskList subtaskList = t.getCacheMap( SubtaskList.class );
      if ( subtaskList == null )
      {
         subtaskList = t.putCacheMap( SubtaskList.class, new SubtaskList() );
      }

      int subtask = subtaskList.dialogCount.incrementAndGet( );
      subtaskList.subtaskMap.putIfAbsent( subtask, new SubtaskItem( v, dialogTag, windowTag, command ) );

      TraceLocal( "Subtask: %d  created for Task: %s   View: %s", subtask, t.getTaskId(), v.getId() );
      return subtask;
   }

   public static int CreateSubtaskForView( View vSubtask )
   {
      // Generated VML uses ints as file handles so we need to convert a File to a int.
      // We'll increment a file count and store the File in a concurrent hashmap.
      View v = VmlOperation.getView( vSubtask );
      Task t = vSubtask.getTask( );
      SubtaskList subtaskList = t.getCacheMap( SubtaskList.class );
      if ( subtaskList == null )
      {
         subtaskList = t.putCacheMap( SubtaskList.class, new SubtaskList() );
      }

      int subtask = subtaskList.dialogCount.incrementAndGet( );
      subtaskList.subtaskMap.putIfAbsent( subtask, new SubtaskItem( v ) );
      System.out.println("ConcurrentHashMap after put: " + subtaskList );
      TraceLocal( "Subtask: %d  created for Task: %s   View: %s", subtask, t.getTaskId(), v.getId() );
      return subtask;
   }

   public static int SetViewForSubtask( TaskQualification taskQual, int subtask, View vSubtask )
   {
      SubtaskList subtaskList = (SubtaskList) taskQual.getTask( ).getCacheMap( SubtaskList.class );
      SubtaskItem subtaskItem = subtaskList.subtaskMap.get( subtask );
      if ( subtaskItem != null )
      {
         subtaskItem.view = vSubtask;
         return subtask;
      }
      else
      {
         return -1;
      }
   }

   public static int DropDialogWindowForHandle( TaskQualification taskView, int subtask )
   {
      SubtaskList subtaskList = (SubtaskList) taskView.getTask().getCacheMap( SubtaskList.class );
   // SubtaskItem item = subtaskList.subtaskMap.get( subtask );
      subtaskList.subtaskMap.remove( subtask );

      return 0;
   }

   private static int getDialog( SubtaskList subtaskList, String dialogName )
   {
      int subtask;

      Iterator<Integer> it = subtaskList.subtaskMap.keySet().iterator();
      while( it.hasNext() )
      {
         subtask = it.next();
         SubtaskItem item = subtaskList.subtaskMap.get( subtask );
         if ( item.dialogTag.equalsIgnoreCase( dialogName ) )
         {
            return subtask;
         }
      }

      return -1;
   }

   public static int FindDialogInApplicationList( TaskQualification taskView, String dialogName, boolean allTasks )
   {
      int subtask = -1;

      if ( allTasks )
      {
         ObjectEngine oe = taskView.getObjectEngine();
         for ( Task t : oe.getTaskList( ) )
         {
            SubtaskList subtaskList = (SubtaskList) t.getCacheMap( SubtaskList.class );
            if ( subtaskList != null )
            {
               subtask = getDialog( subtaskList, dialogName );
               if ( subtask != -1 )
               {
                  break;
               }
            }
         }
      }
      else
      {
         SubtaskList subtaskList = (SubtaskList) taskView.getTask( ).getCacheMap( SubtaskList.class );
         if ( subtaskList != null )
         {
            subtask = getDialog( subtaskList, dialogName );
         }
      }

      return subtask;
   }

   //./ ADD NAME=fnValidSubtaskView
   /////////////////////////////////////////////////////////////////////////////
   //
   //  FUNCTION:   fnValidSubtaskView
   //
   //  ASSUMES:    View passed is a valid subtask view
   //
   //  RETURNS:    >= 0 - View valid
   //                -1 - View invalid
   //
   /////////////////////////////////////////////////////////////////////////////
   public static int fnValidSubtaskView( View vSubtask )
   {
      if ( VmlOperation.isValid( vSubtask ) )
      {
         View v = VmlOperation.getView( vSubtask );
         Task t = v.getTask();
         int subtask;
         SubtaskList subtaskList = (SubtaskList) t.getCacheMap( SubtaskList.class );
         if ( subtaskList != null )
         {
            Iterator<Integer> it = subtaskList.subtaskMap.keySet().iterator();
            while ( it.hasNext() )
            {
               subtask = it.next();
               SubtaskItem item = subtaskList.subtaskMap.get( subtask );
               if ( item.view == v )
               {
                  TraceLocal( "ValidSubtaskView Subtask: %d  for Task: %s   subtask: %s", vSubtask.getId(), t.getTaskId(), subtask );
                  return subtask;
               }
            }

            // It could be a subtask for the System task ... let's check.
            if ( t != t.getSystemTask() )
            {
               t = t.getSystemTask();
               subtaskList = (SubtaskList) t.getCacheMap( SubtaskList.class );
               it = subtaskList.subtaskMap.keySet().iterator();
               while ( it.hasNext() )
               {
                  subtask = it.next();
                  SubtaskItem item = subtaskList.subtaskMap.get( subtask );
                  if ( item.view == v )
                  {
                     TraceLocal( "ValidSubtaskView Subtask: %d  for Task: %s   subtask: %s", vSubtask.getId(), t.getTaskId(), subtask );
                     return subtask;
                  }
               }

               TraceLocal( "InvalidSubtaskView Subtask: %d  for Task: %s  SystemTask: %s", vSubtask.getId(), v.getTask().getTaskId(), t.getTaskId() );
            }
            else
            {
               TraceLocal( "InvalidSubtaskView Subtask: %d  for SystemTask: %s", vSubtask.getId(), t.getTaskId() );
            }
         }
         else
         {
            TraceLocal( "InvalidSubtaskView Subtask: %d  for Task: %s", vSubtask.getId(), t.getTaskId() );
         }
      }

      return( -1 );
   }

   public static boolean isValidSubtaskView( View vSubtask )
   {
      int subtask = fnValidSubtaskView( vSubtask );
      return (subtask == -1) ? false : true;
   }

   public static View fnGetSubtaskViewByName( View vSubtask, zVIEW returnView, String name )
   {
      int subtask = fnValidSubtaskView( vSubtask );
      if ( subtask >= 0 )
      {
         return vSubtask.getViewByNameForSubtask( name );
      }
      
      return null;
   }

   //./ ADD NAME=SfGetFirstNamedView
   // Source Module=kzoevmaa.c
   /////////////////////////////////////////////////////////////////////////////
   //
   //  ENTRY:      SfGetFirstNamedView
   //
   //  RETURNS:   -1 - View not found.
   //              1 - Level of the view found is zLEVEL_SUBTASK
   //              2 - Level of the view found is zLEVEL_TASK
   //              4 - Level of the view found is zLEVEL_APPLICATION
   //              8 - Level of the view found is zLEVEL_SYSTEM
   //    zCALL_ERROR - error in call
   //task.getViewList() will return a list of all views.  task.getViewNameList(View view) will return the list of names for a view.
   //View.getNameList() will also return the list of names for a view.
   /////////////////////////////////////////////////////////////////////////////
   public static int SfGetFirstNamedView( zVIEW  pvReturnView,
                                          StringBuilder  sbReturnName,
                                          View   lpView,
                                          int    nLevel )
   {
      Task t = lpView.getTask();

      pvReturnView.setView( null );
      sbReturnName.setLength( 0 );
      
      switch( nLevel )
      {
         case zLEVEL_APPLICATION:
            Application app = t.getApplication();
            if ( app.getAllNamedViews().isEmpty() == false )
            {
               for ( View v : app.getAllNamedViews() )
               {
                  for ( String s : app.getAllViewNames( v ) )
                  {
                     if ( s.isEmpty() == false )
                     {
                        pvReturnView.setView( v );
                        sbReturnName.append( s );
                        return 0;  // can't use break
                     }
                  }
               }
            }

            break;
            
         case zLEVEL_SYSTEM:
            t = t.getSystemTask();
            // intentional fall through
         case zLEVEL_TASK:
         case zLEVEL_SUBTASK:
            if ( t.getViewList().isEmpty() == false )
            {
               for ( View v : t.getViewList() )
               {
                  for ( String s : v.getNameList() )
                  {
                     if ( s.isEmpty() == false )
                     {
                        pvReturnView.setView( v );
                        sbReturnName.append( s );
                        return 0;  // can't use break
                     }
                  }
               }
            }

            break;
      }
      
      return -1;
   }

   //./ ADD NAME=SfGetNextNamedView
   // Source Module=kzoevmaa.c
   /////////////////////////////////////////////////////////////////////////////
   //
   //  ENTRY:      SfGetNextNamedView
   //
   //  RETURNS:   -1 - View not found.
   //              1 - Level of the view found is zLEVEL_SUBTASK
   //              2 - Level of the view found is zLEVEL_TASK
   //              4 - Level of the view found is zLEVEL_APPLICATION
   //              8 - Level of the view found is zLEVEL_SYSTEM
   //    zCALL_ERROR - error in call
   //
   /////////////////////////////////////////////////////////////////////////////
   public static int SfGetNextNamedView( zVIEW    pvReturnView,
                                         StringBuilder sbReturnName,
                                         View     lpView,
                                         int      nLevel )
   {
      Task t = lpView.getTask();
      boolean viewFound = false;

      pvReturnView.setView( null );
      sbReturnName.setLength( 0 );
      switch( nLevel )
      {
         case zLEVEL_APPLICATION:
            Application app = t.getApplication();
            if ( app.getAllNamedViews().isEmpty() == false )
            {
               for ( View v : app.getAllNamedViews() )
               {
                  if ( v == lpView )
                  {
                     viewFound = true;
                  }
                  else
                  if ( viewFound )
                  {
                     for ( String s : app.getAllViewNames( v ) )
                     {
                        if ( s.isEmpty() == false )
                        {
                           pvReturnView.setView( v );
                           sbReturnName.append( s );
                           return 0;  // can't use break
                        }
                     }
                  }
               }
            }

            break;
            
         case zLEVEL_SYSTEM:
            t = t.getSystemTask();
            // intentional fall through
         case zLEVEL_TASK:
         case zLEVEL_SUBTASK:
            if ( t.getViewList().isEmpty() == false )
            {
               for ( View v : t.getViewList() )
               {
                  if ( v == lpView )
                  {
                     viewFound = true;
                  }
                  else
                  if ( viewFound )
                  {
                     for ( String s : v.getNameList() )
                     {
                        if ( s.isEmpty() == false )
                        {
                           pvReturnView.setView( v );
                           sbReturnName.append( s );
                           return 0;  // can't use break
                        }
                     }
                  }
               }
            }

            break;
      }

      return -1;      
   }

}
