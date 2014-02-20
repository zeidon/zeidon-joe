#!/bin/bash

export ZEIDON_HOME=../../risk-evaluator/src/main/resources

pushd ../../zeidon-tests
cp="`mvn dependency:build-classpath | grep -v INFO`"
echo "classpath=$cp"
popd

echo "here 1"
cp="$cp:$ZEIDON_HOME"
echo "here 2"

jruby -J-cp $cp -S jmxmanager.rb