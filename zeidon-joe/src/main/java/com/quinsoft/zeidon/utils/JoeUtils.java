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

import java.io.File;
import java.io.StringWriter;
import java.lang.management.ManagementFactory;
import java.net.URL;
import java.util.Enumeration;
import java.util.regex.Pattern;

import javax.management.InstanceNotFoundException;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.swing.JOptionPane;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;
import org.joda.time.format.DateTimePrinter;

import com.google.common.collect.ImmutableMap;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;

/**
 * @author DG
 *
 */
public class JoeUtils
{
    private final static Pattern PIPE_DELIMITER = Pattern.compile( "\\|" );

    /**
     * Returns an input stream for a resource/filename.  Logic will first attempt to find
     * a filename that matches the resourceName (search is case-insensitive).  If a file
     * is found, the stream is for the file.
     *
     * If a file is not found, an attempt is made to find the resource on the classpath.
     *
     * @param task - If not null then the ZEIDON_HOME directory will be searched if all
     *                other attempts fail.
     * @param resourceName - Name of resource to open.
     * @param classLoader - ClassLoader used to find resources.  If null then the system
     *                class loader is used.
     *
     * @return the InputStream or null if it wasn't found.
     */
    public static ZeidonInputStream getInputStream( Task task, String resourceName, ClassLoader classLoader )
    {
        // If the resourceName contains a '|' then it is a list of resources.  We'll return the first
        // one that is valid.
        String[] resourceList = PIPE_DELIMITER.split( resourceName );
        if ( resourceList.length > 1 )
        {
            for ( String resource: resourceList )
            {
                ZeidonInputStream stream = getInputStream( task, resource.trim(), classLoader );
                if ( stream != null )
                    return stream;
            }

            // If we get here then none of the resources in the list were found so return null.
            return null;
        }

        try
        {
            //
            // Default is to assume resourceName is a filename.
            //
            File file = getFile( resourceName );
            if ( file.exists() )
                return ZeidonInputStream.create( file );

            if ( classLoader == null )
            {
                if ( task != null )
                    classLoader = task.getClass().getClassLoader();
                if ( classLoader == null )
                    classLoader = new JoeUtils().getClass().getClassLoader();
                if ( classLoader == null )
                    classLoader = resourceName.getClass().getClassLoader();
                if ( classLoader == null )
                    classLoader = ClassLoader.getSystemClassLoader();
            }

            //
            // Try loading as a resource (e.g. from a .jar).
            //
            int count = 0;
            ZeidonInputStream stream = null;
            for ( Enumeration<URL> url = classLoader.getResources( resourceName ); url.hasMoreElements(); )
            {
                count++;
                if ( count > 1 )
                    throw new ZeidonException( "Found multiple resources that match resourceName %s", resourceName );

                stream = ZeidonInputStream.create( url.nextElement() );
            }

            if ( stream != null )
                return stream;

            //
            // Try loading as a lower-case resource name.
            //
            String name = FilenameUtils.getName( resourceName );
            if ( StringUtils.isBlank( name ) )
                return null;

            String path = FilenameUtils.getPath( resourceName );
            String newName;
            if ( StringUtils.isBlank( path ) )
                newName = name.toLowerCase();
            else
                newName = path + name.toLowerCase();

            stream = ZeidonInputStream.create( classLoader, newName );
            if ( stream != null )
                return stream;

            // If task is null then we don't know anything else to try.
            if ( task == null )
                return null;

            //
            // Try loading with ZEIDON_HOME prefix.
            //
            newName = task.getObjectEngine().getHomeDirectory() + "/" + resourceName;
            file = getFile( newName );
            if ( file.exists() )
                return ZeidonInputStream.create( file );

            return null;
        }
        catch (Exception e)
        {
            throw ZeidonException.wrapException( e ).prependFilename( resourceName );
        }
    }

    public static ZeidonInputStream getInputStream( String resourceName, ClassLoader classLoader )
    {
        return getInputStream( null, resourceName, classLoader );
    }

    public static ZeidonInputStream getInputStream( Task task, String resourceName )
    {
        return getInputStream( task, resourceName, null );
    }

    public static ZeidonInputStream getInputStream( String resourceName )
    {
        return getInputStream( null, resourceName, null );
    }

    /**
     * Returns java.io.File for a file matching path using CASE-INSENSITIVE file
     * matching.  This makes it easier for us to run on Windows.
     *
     * @param path
     * @return
     */
    public static File getFile( String path )
    {
        try
        {
            File file = new File( path );
            if ( file.exists() )
                return file;

            // Try searching for the file without regard to case.
            String parentPath = file.getParent();
            if ( StringUtils.isBlank( parentPath ) )
                return file; // No parent path so just return.

            File dir = new File( parentPath );
            NameFileFilter filter = new NameFileFilter( file.getName(), IOCase.INSENSITIVE );
            String[] files = dir.list( filter );
            if ( files == null )
                return file; // We probably didn't find a directory so just return the unknown file.

            if ( files.length == 1 )
                return new File( parentPath + File.separator + files[ 0 ] );

            if ( files.length > 1 )
                throw new ZeidonException( "Found multiple matching entries for %s", path );

            // We didn't find an exact match so just return.
            return file;
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e ).prependFilename( path );
        }
    }

    public static String getEnvProperty( String name )
    {
        return getEnvProperty( name, false );
    }

    public static String getEnvProperty( String name, boolean required )
    {
        String value = System.getProperty( name );
        if ( value == null )
            value = System.getenv( name );

        if ( required && value == null )
            throw new ZeidonException( "Required environment variable '%s' is null", name );

        return value;
    }

    /**
     * Takes the filename and replaces control characters.
     *
     *   .     - If filename starts with '.' then it is prefixed with the CWD.
     *   %var% - Replaces %var% with the environment variable value of 'var'.
     *
     * @param filename
     * @return
     */
    public static String parseFilename( String filename )
    {
        String returnString = filename;

        String[] arr = filename.split( "%" );
        // There must be an even-numbered number of % signs.  If there's not then
        // there will be an even-numbered number of strings in the array.
        if ( arr.length % 2 == 0 )
            throw new ZeidonException("Filename '%s' has an un-paired percent sign", filename );

        if ( arr.length > 0 )
        {
            returnString = "";
            for ( int i = 0; i < arr.length; i++ )
                returnString += ( i % 2 ) == 0 ? arr[i] : getEnvProperty(  arr[i], true );
        }

        if ( returnString.startsWith( ".." ) )
            returnString = System.getProperty("user.dir") + File.separator + returnString;
        else
            if ( returnString.startsWith( "." ) )
                returnString = System.getProperty("user.dir") + File.separator + returnString.substring( 1 );

        return returnString;
    }

    /**
     * A simple wrapper around String.format.  If 'strings' is an empty list then we return 'format', otherwise
     * we call String.format using 'format' and 'strings'.
     *
     * @param format
     * @param strings
     * @return
     */
    public static String format( String format, Object...strings )
    {
        if ( strings.length == 0 )
            return format;

        //TODO: String.format() creates a new Formatter each time.  Is there a faster
        // formatter available or should we create a ThreadLocal cache?
        return String.format( format, strings );
    }

    /**
     * Returns true if str consists only of digits.  For performance sake we don't check
     * for null or empty strings and assume the caller has already done it.  This is faster
     * than using regex.
     *
     * @param string Non-null string.
     * @return False if the string contains any non-digit.
     */
    public static boolean onlyDigits( String str )
    {
        int length = str.length();
        for (int i = 0; i < length; i++)
        {
            char c = str.charAt(i);
            if (c <= '/' || c >= ':')
                return false;
        }

        return true;
    }

    /**
     * Returns a DateTimeFormatter that can parse and print dates in the format of
     * editString.  There can be multiple edit strings which are separated by a "|"
     * character.  If there are more than one then the first one is considered to
     * be the "print" format.
     *
     * @param editString
     * @return
     */
    public static DateTimeFormatter createDateFormatterFromEditString( String editString )
    {
        String[] strings = editString.split( "\\|" );
        if ( strings.length == 1 )
            return DateTimeFormat.forPattern( strings[0] );

        DateTimeParser list[] = new DateTimeParser[ strings.length ];
        DateTimePrinter printer = null;
        for ( int i = 0; i < strings.length; i++ )
        {
            try
            {
                DateTimeFormatter f = DateTimeFormat.forPattern( strings[i] );
                if ( printer == null )
                    printer = f.getPrinter();

                list[ i ] = f.getParser();
            }
            catch ( Exception e )
            {
                throw ZeidonException.wrapException( e ).appendMessage( "Format string = %s", strings[i] );
            }
        }

        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
        builder.append( printer, list );
        DateTimeFormatter formatter = builder.toFormatter();
        return formatter;
    }

    /**
     * Converts a string in the standard Zeidon internal format (yyyyMMddHHmmssSSS) into a
     * DateTime.
     *
     * NOTE: For sake of speed this method does no validation on the input string.
     *
     * @param str
     * @return
     */
    public static DateTime parseStandardDateString( String str )
    {
        assert str.length() >= ObjectEngine.INTERNAL_DATE_STRING_FORMAT.length();

        String t;

        t = str.substring( 0, 4 );
        int year = Integer.parseInt( t );

        t = str.substring( 4, 6 );
        int month = Integer.parseInt( t );

        t = str.substring( 6, 8 );
        int day = Integer.parseInt( t );

        if ( str.length() == ObjectEngine.INTERNAL_DATE_STRING_FORMAT.length() )
            return new DateTime( year, month, day, 0, 0, 0, 0 );

        int hour = 0, minute = 0, seconds = 0, millis = 0;
        switch ( str.length() )
        {
            case 15:
            case 16:
            case 17:
            case 18:
                t = str.substring( 14 );
                millis = Integer.parseInt( t );

            case 13:
            case 14:
                t = str.substring( 12, 14 );
                seconds = Integer.parseInt( t );

            case 11:
            case 12:
                t = str.substring( 10, 12 );
                minute = Integer.parseInt( t );


            case  9:
            case 10:
                t = str.substring( 8, 10 );
                hour = Integer.parseInt( t );
                break; // Yes, break here.

            default:
                throw new ZeidonException( "Invalid length for date string" );
        }

        return new DateTime( year, month, day, hour, minute, seconds, millis );
    }

    /**
     * A map to convert Zeidon date formats to Java date formats.
     */
    static final ImmutableMap<String, String> DATE_FORMAT_CONVERTER =
            new ImmutableMap.Builder<String, String>()
            .put("M", "M")
            .build();

    /**
     * Converts the old Zeidon date format specification to the Java specification so
     * that we can use Java conversion.  Zeidon specification is:
     *
//       Characters     Replaced By
//
//          M/m         A one or two digit number that represents the
//                      month.
//
//         MM/mm        A two digit number that represents the month.
//
//          MON         A three character abbreviation for the month.
//
//          mmm         Month's three letter abbreviation. This symbol
//                      is case sensative; You can specify capitalization
//                      in the formatted value as follows:
//                         mmm   jan
//                         Mmm   Jan
//                         MMM   JAN
//
//          mmmm        Month's full name.  This symbol is case sensative;
//                      you can specify capitalization in the formatted
//                      value as follows:
//
//                         mmmm  january
//                         Mmmm  January
//                         MMMM  JANUARY
//
//           D/d        A one or two digit number that represents the day
//                      of the month.
//
//          DD/dd       A two digit number that represents the day
//                      of the month.
//          ddd         Day of week, three letter abbreviation.  This
//                      symbol is case sensative; You can specify
//                      capitalization is the formatted value as follows:
//                         ddd   sun
//                         Ddd   Sun
//                         DDD   SUN
//
//          dddd        Day of week, full name.  This symbol is case
//                      sensative; You can specify capitalization is the
//                      formatted value as follows:
//                         dddd  sunday
//                         Dddd  Sunday
//                         DDDD  SUNDAY
//
//          jjj         Julian day.
//
//           YY         The last two digits of the year.
//
//          YYYY        The four digits of the year.
//
//           HH         A two digit number that represents hours based
//                      on a 24 hour clock, unless AM or PM is present
//                      in the Edit String, in which case the base is a
//                      12 hour clock
//
//           MI         A two digit number that represents minutes.
//
//           SS         A two digit number that represents seconds.
//
//        AM or PM      Two characters that represents AM or PM.  Either
//                      of these will cause any HH in the Edit String
//                      to be based on a 12 hour clock.
//
//            9         A number with 1 to 3 digits
//           99         that represents
//          999         fractions of a second.
//
//      The following Edit String characters are moved from the Edit
//      String to the Return String as is:
//
//           (      left paren
//           )      right paren
//           :      colon
//           ,      comma
//           -      dash
//           /      slash
//           .      period
//                  space
//
//      The following delimiters may be used to put string constants in
//      the returned string.
//           "      quote
//           '      Apostrophe
     * @param zeidonFormat
     * @return
     */
    public static String convertZeidonDateFormatToJavaFormat( String zeidonFormat )
    {
        // We'll use a map to quickly convert common usage.
        String javaFormat = DATE_FORMAT_CONVERTER.get( zeidonFormat );
        if ( javaFormat != null )
            return javaFormat;

        // TODO: Implement full conversion.
        throw new ZeidonException("Unsupported Zeidon date string.  You may need to update JoeUtils.DATE_FORMAT_CONVERTER.");
    }

    public static final void sysMessageBox( String msgTitle, String msgText )
    {
        JOptionPane.showMessageDialog( null, msgText, msgTitle, JOptionPane.PLAIN_MESSAGE );
    }

    /**
     * Write an OI to a string in portable file format.
     *
     * @param view
     * @return
     */
    public static String serializeView( View view )
    {
        StringWriter writer = new StringWriter();
        view.writeOi( writer );
        return writer.toString();
    }

    public static void RegisterJmxBean( Object bean, String beanName, String jmxAppName )
    {
        try
        {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

            if ( ! StringUtils.isBlank( jmxAppName ) )
                beanName += ",app=" + jmxAppName;

            ObjectName name = new ObjectName( beanName );

            // Make sure the bean doesn't already exist.  If it does, unregister it.
            try
            {
                mbs.getMBeanInfo( name );  // Throws InstanceNotFoundException if not found.
                mbs.unregisterMBean( name ); // Unregister a bean if it exists.
            }
            catch ( InstanceNotFoundException e )
            {
                // If we get here then the mbean isn't currently registered.  This is valid
                // so we'll ignore it.
            }

            mbs.registerMBean(bean, name);
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e )
                                 .appendMessage( "Bean Name = %s, app=%s", beanName, jmxAppName )
                                 .appendMessage( "Bean class = %s", bean.getClass().getName() );

        }

    }
}