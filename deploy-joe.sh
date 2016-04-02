#!/bin/bash

# Deploys JOE to the remote OSSHR repos. Exludes the test projects.
if [[ "$1" == "release" ]]; then
    mvn -P !test-projects,release clean install deploy -DskipTests=true | tee /tmp/deploy.log
else
    mvn -P !test-projects         clean install deploy -DskipTests=true | tee /tmp/deploy.log
fi

#mvn gpg:sign-and-deploy-file -Dfile=sinatra-server/target/zeidon-sinatra-server-1.3.9.jar -DrepositoryId=ossrh -Durl=https://oss.sonatype.org/service/local/staging/deploy/maven2/ -DpomFile=sinatra-server/target/zeidon-sinatra-server-1.3.9.pom
