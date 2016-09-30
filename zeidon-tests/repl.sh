#!/bin/bash

# Runs the Scala REPL (i.e. console) with Zeidon .jars in the classpath.
#TODO: needs jline added to maven dependencies.
mvn scala:console -Djline.inputrc=~/.jline.inputrc
