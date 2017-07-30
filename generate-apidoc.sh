#!/bin/bash

pushd ../zeidon-dev

version=`mvn org.apache.maven.plugins:maven-help-plugin:2.1.1:evaluate     -Dexpression=project.version |grep -Ev '(^\[|Download\w+:)' | egrep -o "[0-9]+\.[0-9]+"`
echo "Creating javadoc for $version"

rm -rf zeidon-joe/target/site/apidocs > /dev/null
rm -rf android/target/site/apidocs > /dev/null
rm -rf scala/target/site/scaladocs > /dev/null

mvn javadoc:javadoc

pushd scala
mvn scala:doc
popd

pushd rest-servers/scalatra
mvn scala:doc
popd

popd

rm -rf javadoc/$version > /dev/null

mkdir -p javadoc/$version/joe javadoc/$version/android javadoc/$version/scala
cp -r ../zeidon-dev/zeidon-joe/target/site/apidocs/* javadoc/$version/joe
cp -r ../zeidon-dev/android/target/site/apidocs/* javadoc/$version/android
cp -r ../zeidon-dev/scala/target/site/scaladocs/* javadoc/$version/scala

if [[ $1 == "--commit" ]]; then
    git add -A javadoc
    git commit -m "Updated javadoc"
    git push
fi
