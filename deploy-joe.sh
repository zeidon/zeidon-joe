#!/bin/bash

# Deploys JOE to the remote OSSHR repos. Exludes the test projects.
#mvn -P !test-projects,release install deploy -DskipTests=true | tee /tmp/deploy.log

mvn -P !test-projects install deploy -DskipTests=true | tee /tmp/deploy.log

mvn gpg:sign-and-deploy-file -Dfile=sinatra-server/target/zeidon-sinatra-server-1.0.7.jar -DrepositoryId=ossrh -Durl=https://oss.sonatype.org/service/local/staging/deploy/maven2/ -DpomFile=sinatra-server/target/zeidon-sinatra-server-1.0.7.pom
