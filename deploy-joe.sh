#!/bin/bash

# Deploys JOE to the remote OSSHR repos. Exludes the test projects.
mvn -P sinatra-server,!test-projects install deploy -DskipTests=true
