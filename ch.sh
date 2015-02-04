#!/bin/bash

sed -ri 's/\.getInternalAttributeValue\(([^\)]*)/.getAttribute(\1).getInternalValue(/g' zeidon-joe/src/main/java/com/quinsoft/zeidon/dbhandler/AbstractSqlHandler.java
sed -ri 's/\.isAttributeNull\(([^\)]*)/.getAttribute(\1).isNull(/g' zeidon-joe/src/main/java/com/quinsoft/zeidon/dbhandler/AbstractSqlHandler.java

cat AbstractSqlHandler.java | perl -ne 'print "$1\n" if /\.getInternalAttributeValue(\((?:[^()]*+|(?0))*\))/x'
