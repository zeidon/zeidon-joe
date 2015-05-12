#!/bin/bash

# Runs the Scala REPL (i.e. console) with Zeidon .jars in the classpath.
mvn scala:console -Djline.inputrc=~/.jline.inputrc
