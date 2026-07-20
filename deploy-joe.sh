#!/bin/bash

# Deploys JOE to the remote OSSHR repos. Exludes the test projects.
if [[ "$1" == "release" ]]; then
    mvn -P !test-projects,deploy clean install deploy -DskipTests=true | tee /tmp/deploy.log
    echo "https://central.sonatype.com/publishing/deployments"
    echo "https://central.sonatype.com/artifact/com.quinsoft.zeidon/zeidon-joe"
else
    mvn -P !test-projects         clean install deploy -DskipTests=true | tee /tmp/deploy.log
    echo "https://central.sonatype.com/repository/maven-snapshots/com/quinsoft/zeidon/zeidon-joe/"
fi

#mvn gpg:sign-and-deploy-file -Dfile=sinatra-server/target/zeidon-sinatra-server-1.3.9.jar -DrepositoryId=central -Durl=https://central.sonatype.com/repository/maven-snapshots/ -DpomFile=sinatra-server/target/zeidon-sinatra-server-1.3.9.pom
