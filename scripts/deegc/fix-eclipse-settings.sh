#!/bin/bash

cd ../..

sed -i 's/1\.4/1.8/g' `find . -name org.eclipse.jdt.core.prefs`
