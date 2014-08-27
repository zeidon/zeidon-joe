#!/bin/bash

# This parses a log file and prints the SQL statements.

if [ -z "$1" ]; then
    echo "ClaimId is required as the first param"
    exit 1
fi

awk '/<\/SQL>/{flag=0;print "-------------------"}flag;/<SQL>/{flag=1}' $1
