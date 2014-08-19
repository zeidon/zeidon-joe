#!/bin/bash

DEBUG_FLAGS=
DEBUG_PORT=9000
DEBUG_FLAGS="-J-Xrunjdwp:transport=dt_socket,address=$DEBUG_PORT,server=y,suspend=n"

while getopts "cdp:" option; do
    case $option in
        c)
            echo "Removing .tmpclasspath"
            rm .tmpclasspath > /dev/null
	    shift
            ;;
        d)
            echo "Starting server in suspend mode"
            DEBUG_FLAGS="-J-Xrunjdwp:transport=dt_socket,address=$DEBUG_PORT,server=y,suspend=y"
	    shift
            ;;
        p)
            echo "Using DEBUG_PORT=$OPTARG"
            DEBUG_PORT="$OPTARG"
	    shift
	    shift
            ;;
    esac
done

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

cp="target/classes:$JAVA_HOME/lib/jconsole.jar:$JAVA_HOME/lib/tools.jar:$JAVA_HOME/classes:$(cat .tmpclasspath)"
#echo "cp=$cp"
#echo "jconsole -J-Djava.class.path=$cp $DEBUG_FLAGS -pluginpath target/zeidon-jconsole-plugin-1.0.7-SNAPSHOT.jar $*"
jconsole -J-Djava.class.path=$cp $DEBUG_FLAGS -pluginpath target/zeidon-jconsole-plugin-1.0.7-SNAPSHOT.jar $* &

