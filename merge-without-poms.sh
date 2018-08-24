#!/bin/bash

# Performs a git merge except for pom files.

if [ -z "$1" ]; then
    echo "First argument must be source branch"
    exit 1
fi

pom_files=$(git ls-files \*pom.xml)

git merge --no-ff --no-commit "$1"
git reset HEAD $pom_files
git checkout -- $pom_files
