#!/bin/bash

export ch_print=N

call_perl() {
    export ch_find="$2"
    export ch_replace="$3"
    perl -ni ch.perl $1
}

files="zeidon-joe/src/main/java/com/quinsoft/zeidon/dbhandler/AbstractSqlHandler.java"

for f in $files; do 
    #call_perl $f getInternalAttributeValue "getInternalValue()"
    #call_perl $f isAttributeNull "isNull()"
    call_perl $f getStringFromAttribute "getString()"
done

#export ch_find="getInternalAttributeValue"
#export ch_replace="getInternalValue()"
#perl -n ch.perl zeidon-joe/src/main/java/com/quinsoft/zeidon/dbhandler/AbstractSqlHandler.java 

