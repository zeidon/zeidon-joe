#!/bin/bash

export ch_print=Y

call_perl() {
    export ch_find="$2"
    export ch_replace="$3"
    perl -ni ch.perl $1
}

files="zeidon-joe/src/main/java/com/quinsoft/zeidon/dbhandler/AbstractSqlHandler.java 
       zeidon-joe/src/main/java/com/quinsoft/zeidon/standardoe/EntityInstanceImpl.java
       zeidon-joe/src/main/java/com/quinsoft/zeidon/standardoe/EntityCursorImpl.java"

for f in $files; do 
    call_perl $f getInternalAttributeValue "getValue()"
    call_perl $f isAttributeNull "isNull()"
    call_perl $f isAttributeUpdated "isUpdated()"
    call_perl $f getStringFromAttribute "getString()"
    call_perl $f getIntegerFromAttribute "getInteger()"
done

#export ch_find="getInternalAttributeValue"
#export ch_replace="getInternalValue()"
#perl -n ch.perl zeidon-joe/src/main/java/com/quinsoft/zeidon/dbhandler/AbstractSqlHandler.java 

