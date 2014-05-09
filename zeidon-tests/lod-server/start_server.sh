#!/bin/bash

DEBUG_FLAGS=
DEBUG_PORT=8000

while getopts "cdxp:" option; do
    case $option in
        c)
            echo "Removing .tmpclasspath"
            rm .tmpclasspath > /dev/null
            ;;
        d)
            echo "Starting server in debug mode"
            DEBUG_FLAGS="-Xrunjdwp:transport=dt_socket,address=$DEBUG_PORT,server=y,suspend=n"
            ;;
        p)
            echo "Using DEBUG_PORT=$OPTARG"
            DEBUG_PORT="$OPTARG"
            ;;
        x)
            echo "Starting with debug in suspend mode"
            DEBUG_FLAGS="-Xrunjdwp:transport=dt_socket,address=$DEBUG_PORT,server=y,suspend=y"
            ;;
    esac
done

export SINATRA_PORT=4568
export ZEIDON_HOME=`pwd`

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

java -cp "$cp" $DEBUG_FLAGS org.jruby.JarBootstrapMain  | tee /tmp/lod-server.log

