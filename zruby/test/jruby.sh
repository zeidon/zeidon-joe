#!/bin/bash

# This runs a Zeidon ruby script using JRuby.  It will use Maven to build
# the classpath.

DEBUG_FLAGS=
DEBUG_PORT=8000

while getopts "cdx:" option; do
    case $option in
        c)
            echo "Removing .tmpclasspath"
            rm .tmpclasspath > /dev/null
            ;;
        d)
            echo "Starting JRuby in debug mode"
            DEBUG_FLAGS="-J-Xrunjdwp:transport=dt_socket,address=$DEBUG_PORT,server=y,suspend=n"
            ;;
        x)
            echo "Starting JRuby with debug in suspend mode"
            DEBUG_FLAGS="-J-Xrunjdwp:transport=dt_socket,address=$DEBUG_PORT,server=y,suspend=y"
            ;;
    esac
done

if [ -z "$1" ]; then
    echo "Script to execute is required"
    exit
fi

#
# Create a temp file with the classpath.
#

# If the temp file exists and is > 24 hours old delete it so we can recreate it.
if [ -f .tmpclasspath ]; then
    if test "`find .tmpclasspath -mmin +1440`"; then
        echo ".tmpclasspath is old and will be regenerated"
        rm .tmpclasspath
    fi
fi

if [ -f .tmpclasspath ]; then
    echo "Using cached classpath (.tmpclasspath)"
else
    echo "Getting classpath..."
    mvn dependency:build-classpath -Dmdep.outputFile=".tmpclasspath" > /dev/null

    # Prepend classpath with current directory to pick up jar-bootstrap.rb
    echo -e ".:$(cat .tmpclasspath)" > .tmpclasspath
fi

cp=`cat .tmpclasspath`
#echo "cp=$cp"

JMXOPTS="-J-Dcom.sun.management.jmxremote
         -J-Dcom.sun.management.jmxremote.port=9010
         -J-Dcom.sun.management.jmxremote.local.only=true
         -J-Dcom.sun.management.jmxremote.authenticate=false
         -J-Dcom.sun.management.jmxremote.ssl=false"

echo "Calling jruby..."
jruby -w -J-cp "$cp" $DEBUG_FLAGS $JMXOPTS $* 2>&1 | tee /tmp/jruby.log
echo "Log is /tmp/jruby.log"
