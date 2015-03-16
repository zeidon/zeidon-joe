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

package com.quinsoft.zeidon;

/**
 * @author DG
 *
 */
public class ObjectConstraintException extends ZeidonException
{
    private static final long serialVersionUID = 1L;
    
    private final View view;
    private final int  rc;

    /**
     * @param format
     * @param strings
     */
    public ObjectConstraintException(View view, int rc, String format, Object... strings)
    {
        super( format, strings );
        this.view = view;
        this.rc = rc;
    }

    public View getView()
    {
        return view;
    }
    
    public int getReturnCode()
    {
        return rc;
    }
}
