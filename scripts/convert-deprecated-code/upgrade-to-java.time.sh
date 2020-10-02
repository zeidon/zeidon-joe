#!/bin/bash

root_dir="$1"
if [ -z "$root_dir" ]; then
    echo "Root directory of project is required as first argument"
    exit 1
fi

java_files=$(find "$root_dir" -type f -name \*.java)

sed -i 's/import org.joda.time.DateTime;/import java.time.ZonedDateTime;/g' $java_files
sed -i 's/import org.joda.time.format.DateTimeFormatter;/import java.time.format.DateTimeFormatter;/g' $java_files
sed -i 's/import org.joda.time.format.DateTimeFormatterBuilder;/import java.time.format.DateTimeFormatterBuilder;/g' $java_files
sed -i 's/ormatter.print(/ormatter.format(/g' $java_files
sed -i 's/ormatter.parseDateTime(/ormatter.parse(/g' $java_files
sed -i 's/DateTimeFormat.forPattern(/DateTimeFormatter.ofPattern(/g' $java_files
sed -i 's/\bDateTime\b/ZonedDateTime/g' $java_files
sed -i 's/new ZonedDateTime()/ZonedDateTime.now()/g' $java_files
sed -i '/org.joda.time.format.DateTimeFormat/d' $java_files

