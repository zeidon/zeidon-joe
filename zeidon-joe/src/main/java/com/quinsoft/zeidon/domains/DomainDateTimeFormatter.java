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
package com.quinsoft.zeidon.domains;

import com.quinsoft.zeidon.ZeidonException;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

/**
 * A DateTime class for parsing strings into ZonedDateTime and formatting ZonedDateTime
 * back into strings.
 *
 * The parser will be constructed to handle multiple input date formats.  This means
 * we need a separate formatter than will output a known format.
 *
 */
public class DomainDateTimeFormatter
{
    private final DateTimeFormatter[] formatters;
    private final String formatString;

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd[['T'][ ]HH:mm:ss[.SSS][.S][xxx][X]]";

    public DomainDateTimeFormatter( String editString )
    {
        try
        {
            if ( StringUtils.isBlank( editString ) )
                throw new ZeidonException( "EditString required for DomainDateTimeFormatter" );

            String[] formats = editString.split( "[|]" );
            formatters = new DateTimeFormatter[ formats.length + 1 ];
            for ( int i = 0; i < formats.length; i++ )
                formatters[ i ] = DateTimeFormatter.ofPattern( formats[ i ].trim() );

            formatters[ formats.length ] = DateTimeFormatter.ofPattern( DEFAULT_DATE_FORMAT );
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e )
                .appendMessage( "Format string = %s", editString );
        }

        this.formatString = editString + " | " + DEFAULT_DATE_FORMAT;
    }

    public String format( ZonedDateTime date )
    {
        return formatters[0].format( date );
    }

    /**
     * Try parsing dateString with all the formatters until we find one that works.
     */
    public ZonedDateTime parse( String dateString )
    {
        for ( DateTimeFormatter parser : formatters )
        {
            try
            {
                return parseDateTimeString( parser, dateString );
            }
            catch ( DateTimeParseException e )
            {
                // Do nothing here.  We'll try parsing with the next formatter.
                System.out.println(e.getMessage());
            }
        }

        throw new ZeidonException( "Invalid datetime format.  Got '%s' but expected format '%s'",  dateString, this.formatString );
    }


    private ZonedDateTime parseDateTimeString( DateTimeFormatter dateTimeFormatter, String dateString )
    {
        TemporalAccessor ta = dateTimeFormatter.parseBest( dateString,
                                                           ZonedDateTime::from,
                                                           LocalDateTime::from,
                                                           LocalDate::from,
                                                           LocalTime::from );

        if ( ta instanceof ZonedDateTime) {
            return (ZonedDateTime) ta;
        }

        if ( ta instanceof LocalDateTime) {
            LocalDateTime lt = LocalDateTime.from( ta );
            ZonedDateTime dt = ZonedDateTime.of( lt, ZoneId.systemDefault());
            return dt;
        }

        if ( ta instanceof LocalDate ) {
            LocalDate ld = (LocalDate) ta;
            ZonedDateTime dt = ld.atStartOfDay(ZoneId.systemDefault());
            return dt;
        }

        if ( ta instanceof LocalTime ) {
            LocalTime lt = (LocalTime) ta;
            ZonedDateTime dt = ZonedDateTime.of(LocalDate.of(1970, 1, 1).atTime(lt), ZoneId.systemDefault());
            return dt;
        }

        ZonedDateTime dt = ZonedDateTime.from( ta );
        return dt;
    }

    @Override
    public String toString()
    {
        return formatString;
    }
}
