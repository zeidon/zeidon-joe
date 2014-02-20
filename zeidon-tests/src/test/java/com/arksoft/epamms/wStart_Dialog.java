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

    Copyright 2009-2010 QuinSoft
**/

package com.arksoft.epamms;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.vml.VmlDialog;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.vml.zVIEW;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableInt;

/**
 * @author QuinSoft
 *
 */

public class wStart_Dialog extends VmlDialog
{
   public wStart_Dialog( View view )
   {
      super( view );
   }


//:DIALOG OPERATION
public int 
ContrivedError( View     ViewToWindow )
{

   //:ContrivedError( VIEW ViewToWindow )

   //:MessageSend( ViewToWindow, "", "Cause Error",
   //:             "Contrived Error!!!", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
   MessageSend( ViewToWindow, "", "Cause Error", "Contrived Error!!!", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );

   //:RETURN 2
   return( 2 );
// END
} 



}
