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

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.quinsoft.zeidon.utils.JoeUtils;

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
    private final DateTimeFormatter parser;
    private final DateTimeFormatter formatter;
    private final String formatString;

    public DomainDateTimeFormatter( DateTimeFormatter parser, DateTimeFormatter formatter, String fmt )
    {
        super();
        this.parser = parser;
        this.formatter = formatter;
        this.formatString = fmt;
    }

    public String format( ZonedDateTime date )
    {
        return formatter.format( date );
    }

    public ZonedDateTime parse( String dateString )
    {
        return JoeUtils.parseDateTimeString( parser, dateString );
    }

    @Override
    public String toString()
    {
        return formatString;
    }
}
