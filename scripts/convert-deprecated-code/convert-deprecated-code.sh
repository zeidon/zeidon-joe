#!/bin/bash

# This edits java files in the zeidon-joe directory and updates the code
# to convert the deprecated code to the new format.

export ch_print=Y

call_perl() {
    export ch_find="$2"
    export ch_replace="$3"
    export ch_args="$4"
    perl -ni ch.perl $1
}

files="../../zeidon-joe/src/main/java/com/quinsoft/zeidon/dbhandler/AbstractSqlHandler.java 
       ../../zeidon-joe/src/main/java/com/quinsoft/zeidon/standardoe/EntityInstanceImpl.java
       ../../zeidon-joe/src/main/java/com/quinsoft/zeidon/standardoe/EntityCursorImpl.java"

#files=`find ../../zeidon-operations -name \*.java`
files="/home/dgc/zeidon/java/zeidon-dev/zeidon-joe/src/main/java/com/quinsoft/zeidon/utils/QualificationBuilder.java"

for f in $files; do 
    call_perl $f getInternalAttributeValue "getValue()" 0
    call_perl $f isAttributeNull "isNull()" 0
    call_perl $f isAttributeUpdated "isUpdated()" 0
    call_perl $f getStringFromAttribute "getString()" 0
    call_perl $f getIntegerFromAttribute "getInteger()" 0
    call_perl $f getDoubleFromAttribute "getDouble()" 0
    call_perl $f getBlobFromAttribute "geBlob()" 0
    call_perl $f getBlobFromAttribute "geBlob()" 0
    call_perl $f compareAttribute "compare" 1
    call_perl $f setInternalAttributeValue "setInternalValue" 1
    call_perl $f setAttribute "setValue" 1
    call_perl $f setAttributeFromAttribute "setValue" 3
done

