#!/bin/bash

# Updates the project version values.  It includes the sinatra server
mvn -P sinatra-server versions:set -DnewVersion=$1 -DgenerateBackupPoms=false
