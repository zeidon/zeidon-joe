#!/bin/bash

# Deploys JOE to the remote OSSHR repos. Exludes the test projects.
mvn -P !test-projects,release install deploy -DskipTests=true | tee /tmp/deploy.log
