#!/bin/bash

# Deploys JOE to the remote OSSHR repos. Exludes the test projects.
if [[ "$1" == "release" ]]; then
    mvn -P !test-projects,release clean install deploy -DskipTests=true | tee /tmp/deploy.log
    echo "https://oss.sonatype.org/#nexus-search;quick~zeidon-joe"
    echo "https://oss.sonatype.org/service/local/repositories/comquinsoft-1051/content/com/quinsoft/zeidon/zeidon-joe/"
else
    mvn -P !test-projects         clean install deploy -DskipTests=true | tee /tmp/deploy.log
    echo "https://oss.sonatype.org/content/repositories/snapshots/com/quinsoft/zeidon/zeidon-joe/"
fi

#mvn gpg:sign-and-deploy-file -Dfile=sinatra-server/target/zeidon-sinatra-server-1.3.9.jar -DrepositoryId=ossrh -Durl=https://oss.sonatype.org/service/local/staging/deploy/maven2/ -DpomFile=sinatra-server/target/zeidon-sinatra-server-1.3.9.pom
