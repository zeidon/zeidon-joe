#!/bin/bash

if [ ! -f pom.xml ]; then pushd ..
else pushd .
fi

mvn versions:set -DnewVersion=$1
rm `find . -name pom.xml.versionsBackup`

popd