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

package com.quinsoft.zeidon.utils;

/**
 * Used to measure elapsed time.
 * 
 * @author DG
 *
 */
public class Timer
{
    private final long start;
    private long finish = 0;

    public Timer()
    {
        start = System.nanoTime();
    }
    
    public void stop()
    {
        finish = System.nanoTime() - start;
    }
    
    public long getNanoTime()
    {
        long l = finish;
        if ( l == 0 )
            l = System.nanoTime();
        return l - start;
    }
    
    public long getMilliTime()
    {
        long l = finish;
        if ( l == 0 )
            l = System.nanoTime();
        return (l - start) / 1000000;
    }
    
    @Override
    public String toString()
    {
        double seconds = getNanoTime() / 1000000000.0;
        return String.format( "%5f", seconds );
    }
    
    public String toString( String header )
    {
        return header + " took " + toString() + " seconds.";
    }
}
