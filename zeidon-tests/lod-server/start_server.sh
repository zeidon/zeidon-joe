#!/bin/bash

DEBUG_FLAGS=
DEBUG_PORT=8000

while getopts "dxp:" option; do
    case $option in
	d)
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
export ZEIDON_HOME=/home/dgc/zeidon/java/zeidon-dev/zeidon-tests/src/test/resources/testdata/
#export ZEIDON_HOME=
echo "java $DEBUG_FLAGS -jar target/lod-server-1.0.0-SNAPSHOT.jar"
java $DEBUG_FLAGS -jar target/test-lod-server-1.0.2.jar
