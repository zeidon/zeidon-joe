#!/bin/bash

cd ../..
PORT=8080

JETTY_DEBUG=
if [ "$1" == "-d" ]; then
    JETTY_DEBUG="-Xdebug -agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n"
fi

classpath="./target/test-classes:$(cat ./target/classpath.txt)"

java -cp "$classpath" -ea -Xmx200m $JETTY_DEBUG com.quinsoft.zeidon.javascript.JettyServer
